<#import "fragments/common.ftlh" as c>

<@c.page>
    List of users
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Username</th>
            <th scope="col">Roles</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <th scope="row">${user.id}</th>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a class="btn btn-primary" href="/users/edit/${user.id}">edit</a></td>
                <td><a class="btn btn-danger" href="/users/delete/${user.id}">delete</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>