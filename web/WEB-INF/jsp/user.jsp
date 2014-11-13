<%-- 
    Document   : user
    Created on : 11.11.2014, 14:03:09
    Author     : User
--%>

<div id="main">
    <div class="inner">
        <div class="parameterRow">
            <div class="mid input">
                <h2 class="small labelH"><fmt:message key="login" bundle="${ rb }" />: </h2>
            </div>
            <div class="input top inner">
                <ctg:StatusTag status="${currUser.status}" ifValid="blu small lft labelH" ifInvalid="grey small lft labelH">
                    <h3>${currUser.login}</h3>
                </ctg:StatusTag>
            </div>
            <div class="mid input">
                <h2 class="small labelH"><fmt:message key="role" bundle="${ rb }" />: </h2>
            </div>
            <div class="input top inner">
                <ctg:StatusTag status="${currUser.status}" ifValid="blu small lft labelH" ifInvalid="grey small lft labelH">
                    <h3>${currUser.role.roleName}</h3>
                </ctg:StatusTag>
            </div>
            <div class="mid input">
                <h2 class="small labelH"><fmt:message key="email" bundle="${ rb }" />: </h2>
            </div>
            <div class="input top inner">
                <ctg:StatusTag status="${currUser.status}" ifValid="blu small lft labelH" ifInvalid="grey small lft labelH">
                    <h3>${currUser.email}</h3>
                </ctg:StatusTag>
            </div>
            <div class="mid input">
                <h2 class="small labelH"><fmt:message key="phoneNumber" bundle="${ rb }" />: </h2>
            </div>
            <div class="input top inner">
                <ctg:StatusTag status="${currUser.status}" ifValid="blu small lft labelH" ifInvalid="grey small lft labelH">
                    <h3>${currUser.phone}</h3>
                </ctg:StatusTag>
            </div>
            <div class="mid input">
                <h2 class="small labelH"><fmt:message key="userDiscount" bundle="${ rb }" />: </h2>
            </div>
            <div class="input top inner">
                <ctg:StatusTag status="${currUser.status}" ifValid="blu small lft labelH" ifInvalid="grey small lft labelH">
                    <h3>${currUser.discount}%</h3>
                </ctg:StatusTag>
            </div>
            <div class="mid input">
                <h2 class="small labelH"><fmt:message key="balance" bundle="${ rb }" />: </h2>
            </div>
            <div class="input top inner">
                <ctg:StatusTag status="${currUser.status}" ifValid="blu small lft labelH" ifInvalid="grey small lft labelH">
                    <h3>${currUser.balance}<fmt:message key="$" bundle="${ rb }" /></h3>
                </ctg:StatusTag>
            </div>
        </div>

        <c:choose>
            <c:when test="${currUser.status == 1}">
                <ctg:RoleUserTag>
                    <input class="large orange awesome" type="submit" value="<fmt:message key="editUser" bundle="${ rb }" />" onclick="postUser('controller', 'goEditUser', 'POST')"/>
                </ctg:RoleUserTag>
                <ctg:RoleTag>
                    <input class="large red awesome" type="submit" value="<fmt:message key="deleteUser" bundle="${ rb }" />" onclick="postUser('controller', 'DeleteUser', 'POST')"/>
                    <ctg:ErrorMsgTag classErr="erNote" msg="${errorDelete}"><fmt:message key="${errorDelete}" bundle="${ rb }" /></ctg:ErrorMsgTag>
                    <ctg:ErrorMsgTag classErr="erNote" msg="${errorDeleteReason}"><fmt:message key="${errorDeleteReason}" bundle="${ rb }" /></ctg:ErrorMsgTag>
                </ctg:RoleTag>
            </c:when>
            <c:when test="${currUser.status == 0}">
                <ctg:RoleTag>
                    <input class="large green awesome" type="submit" value="<fmt:message key="restoreUser" bundle="${ rb }" />" onclick="postUser('controller', 'RestoreUser', 'POST')"/>
                    <ctg:ErrorMsgTag classErr="erNote" msg="${errorRestore}"><fmt:message key="${errorRestore}" bundle="${ rb }" /></ctg:ErrorMsgTag>
                    <ctg:ErrorMsgTag classErr="erNote" msg="${errorRestoreReason}"><fmt:message key="${errorrestoreReason}" bundle="${ rb }" /></ctg:ErrorMsgTag>
                </ctg:RoleTag>
             </c:when>
        </c:choose>   
    </div>
</div>
