<#import "parts/common.ftlh" as c>


<@c.page>
    <div class="form-group col-md-6">
        <h3>
            <p class="text-center ">
                Детали задачи
            </p>
        </h3>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Имя документа</th>
                <th scope="col">Описание</th>
            </tr>
            </thead>
            <tbody>
            <#list user as user>
                <tr>
                    <td>${user.documentName}</td>
                    <td>${user.description}</td>

                </tr>
                <tr>
                    <form method="post" action="/statement">
                        <td><input type="text" name="comment" class="form-control" value="${comment?ifExists}"
                                   style="width: 500px" placeholder="Введите комментарий для принятия"></td>
                        <td>
                            <button class="btn btn-primary mt-0" style="width: 150px">Принять</button>
                        </td>
                        <input type="hidden" name="id" value="${"accept/" + user.id}"/>
                    </form>
                </tr>
                <tr>
                    <form method="post" action="/statement">
                        <td><input type="text" name="comment" class="form-control" value="${comment?ifExists}"
                                   style="width: 500px" placeholder="Введите комментарий для отмены"></td>
                        <td>
                            <button class="btn btn-primary mt-0" style="width: 150px">Отклонить</button>
                        </td>
                        <input type="hidden" name="id" value="${"decline/" + user.id}"/>
                    </form>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

</@c.page>