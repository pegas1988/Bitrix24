<#import "parts/common.ftlh" as c>


<@c.page>
    <div class="form-group col-md-6">
        <h3>
            <p class="text-center ">
                Список задач
            </p>
        </h3>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Название</th>
            </tr>
            </thead>
            <tbody>
            <#list list as user>
                <tr>
                    <td>${user.name}</td>
                    <td>
                        <form method="post">
                            <button class="btn btn-primary mt-0" style="width: 150px">Детальнее</button>
                            <input type="hidden" name="id" value="${user.id}"/>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

</@c.page>