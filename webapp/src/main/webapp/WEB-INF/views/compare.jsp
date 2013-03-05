<%@ include file="../include/includes.jsp"%>
<a:page>
    <c:choose>
        <c:when test="${not empty uniqueFriends}">
            <p>There are the following unique friends between <b>${bookOne}</b> and <b>${bookTwo}</b></p>
            <ul>                    
            <c:forEach items="${uniqueFriends}" var="friend">
                <li>${friend.name}</li>
            </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>There were no unique friends between <b>${bookOne}</b> and <b>${bookTwo}</b></p>
        </c:otherwise>
    </c:choose>
</a:page>