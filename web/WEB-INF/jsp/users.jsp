<%-- 
    Document   : users
    Created on : 11.11.2014, 14:03:47
    Author     : User
--%>

<div id="main">
    <div class="inner">
        <div class="parameterRow">
            <table class="border padB" id="users">
                <colgroup>
                    <col class="login"/>
                    <col class="email"/>
                    <col class="phone" />
                    <col class="discount" />
                    <col class="balance" />
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col"><fmt:message key="login" bundle="${ rb }" /></th>
                        <th scope="col"><fmt:message key="email" bundle="${ rb }" /></th>
                        <th scope="col"><fmt:message key="phoneNumber" bundle="${ rb }" /></th>
                        <th scope="col"><fmt:message key="discount" bundle="${ rb }" />, %</th>
                        <th scope="col"><fmt:message key="balance" bundle="${ rb }" />, <fmt:message key="$" bundle="${ rb }" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="usr" items="${userList}">
                        <c:choose>
                            <c:when test="${usr.status == 1}">
                                <tr>
                                    <td class="login"><a href="controller?command=showUser&selectId=${usr.idUser}">${usr.login}</a></td>
                                    <td class="email">${usr.email}</td>
                                    <td class="phone">${usr.phone}</td>
                                    <td class="discount">${usr.discount}</td>
                                    <td class="balance">${usr.balance}</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr class="greyBG">
                                    <td class="login"><a href="controller?command=showUser&selectId=${usr.idUser}">${usr.login}</a></td>
                                    <td class="email">${usr.email}</td>
                                    <td class="phone">${usr.phone}</td>
                                    <td class="discount">${usr.discount}</td>
                                    <td class="balance">${usr.balance}</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
