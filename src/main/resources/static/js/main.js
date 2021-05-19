$(document).ready(function () {
    restartAllUser();
    $('.btn-success').on('click', function (event) {
        let user = {
            name: $("#name").val(),
            age: $("#age").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            roles: getRole("#selectRole")
        }
        console.log(user);
        if (valid() === true) {
            fetch("api/newUser", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                },
                body: JSON.stringify(user)
            }).then(() => openTabById('nav-home'))
                .then(() => restartAllUser());
            $('input').val('');
        }
    });
});

function createTableRow(u) {
    let userRole = "";
    for (let i = 0; i < u.roles.length; i++) {
        userRole += " " + u.roles[i].role;
    }
    return `<tr id="user_table_row">
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.age}</td>
            <td>${u.username}</td>
            <td>${u.password}</td>
            <td>${userRole}</td>
            <td>
            <a  href="/api/${u.id}" class="btn btn-info eBtn" >Edit</a>
            </td>
            <td>
            <a  href="/api/delete/${u.id}" class="btn btn-danger delBtn">Delete</a>
            </td>
        </tr>`;
}

function getRole(address) {
    let data = [];
    $(address).find("option:selected").each(function () {
        data.push({id: $(this).val(), role: $(this).attr("name"), authority: $(this).attr("name")})
    });
    return data;
}

function restartAllUser() {
    let UserTableBody = $("#user_table_body")

    UserTableBody.children().remove();

    fetch("api/allusers")
        .then((response) => {
            response.json().then(data => data.forEach(function (item) {
                let TableRow = createTableRow(item);
                UserTableBody.append(TableRow);
            }));
        }).catch(error => {
        console.log(error);
    });
}

function valid() {
    let flag = true;
    $(".error").remove();
    if ($('#name').val().length < 3) {
        $('#name').after('<span class="error alert-danger">Укажите имя, не менее 3х символов</span>');
        flag = false;
    }
    if ($('#age').val() < 1 || $('#age').val() > 102) {
        $('#age').after('<span class="error alert-danger">Укажите корректный возраст(от 1 до 102)</span>');
        flag = false;
    }
    if (!Number.isInteger($('#age').val())) {
        $('#age').after('<span class="error alert-danger">Возраст должен быть целым числом</span>');
        flag = false;
    }
    if ($('#username').val().length < 1) {
        $('#username').after('<span class="error alert-danger">Укажите почту</span>');
        flag = false;
    } else {
        let regEx = /@/
        let validEmail = regEx.test($('#username').val())
        if(!validEmail) {
            $('#username').after('<span class="error alert-danger">Некорректный формат почты</span>');
            flag = false;
        }
    }
    if ($('#password').val().length < 4) {
        $('#password').after('<span class="error alert-danger">Пароль не менее 4 символов</span>');
        flag = false;
    }
    return flag
}

function validModal() {
    let flagValid = true;
    $(".error").remove();
    if ($('#nameEd').val().length < 3) {
        $('#nameEd').after('<span class="error alert-danger">Укажите имя, не менее 3х символов</span>');
        flagValid = false;
    }
    if ($('#ageEd').val() < 1 || $('#ageEd').val() > 102 ) {
        $('#ageEd').after('<span class="error alert-danger">Укажите корректный возраст(от 1 до 102)</span>');
        flagValid = false;
    }
     else if (!Number.isInteger($('#ageEd').val())) {
        $('#ageEd').after('<span class="error alert-danger">Возраст должен быть целым числом</span>');
        flagValid = false;
    }
    if ($('#usernameEd').val().length < 1) {
        $('#usernameEd').after('<span class="error alert-danger">Укажите почту</span>');
        flagValid = false;
    } else {
        let regEx = /@/
        let validEmail = regEx.test($('#usernameEd').val())
        if(!validEmail) {
            $('#usernameEd').after('<span class="error alert-danger">Некорректный формат почты</span>');
            flagValid = false;
        }
    }
    if ($('#passwordEd').val().length < 4) {
        $('#passwordEd').after('<span class="error alert-danger">Пароль не менее 4 символов</span>');
        flagValid = false;
    }
    return flagValid
}


document.addEventListener('click', function (event) {
    event.preventDefault()

    if ($(event.target).hasClass('delBtn')) {
        let href = $(event.target).attr("href");
        delModalButton(href)
    }

    if ($(event.target).hasClass('eBtn')) {
        let href = $(event.target).attr("href");
        $(".editUser #exampleModal").modal();

        $.get(href, function (user) {
            $(".editUser #id").val(user.id);
            $(".editUser #nameEd").val(user.name);
            $(".editUser #ageEd").val(user.age);
            $(".editUser #usernameEd").val(user.username);
            $(".editUser #passwordEd").val(user.password);
            $(".editUser #selectRoleEd").val(user.roles);
        });
    }
    if ($(event.target).hasClass('editButton')) {
        let user = {
            id: $('#id').val(),
            name: $('#nameEd').val(),
            age: $('#ageEd').val(),
            username: $('#usernameEd').val(),
            password: $('#passwordEd').val(),
            roles: getRole("#selectRoleEd")
        }
        console.log(user);
        if (validModal() === true) {
            editModalButton(user)
        }

    }

    if ($(event.target).hasClass('logout')) {
        logout();
    }
    if ($(event.target).hasClass('btnUserTable')) {
        userTable();
    }
});

function delModalButton(href) {
    fetch(href, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        }
    }).then(() => restartAllUser());
}

function editModalButton(user) {
    fetch("api/edit", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(user)
    }).then(function (response) {
        $('input').val('');
        $('.editUser #exampleModal').modal('hide');
        restartAllUser();
    })
}

function openTabById(tab) {
    $('.nav-tabs a[href="#' + tab + '"]').tab('show');
}

function logout() {
    document.location.replace("/logout");
}

function userTable() {
    document.location.replace("/user");
}


