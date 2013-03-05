<%@ include file="../../include/includes.jsp"%>
<c:url value="/addressbook" var="action"/>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Address Book</title>
<link rel="stylesheet" href="<c:url value='/resources/css/reset.css'/>"type="text/css" media="screen, projection" />
<link rel="stylesheet" href="<c:url value='/resources/css/text.css'/>" type="text/css" media="screen, projection"/>
<link rel="stylesheet" href="<c:url value='/resources/css/960.css'/>" type="text/css" media="screen, projection"/>
<link rel="stylesheet" href="<c:url value='/resources/css/demo.css'/>" type="text/css" media="screen, projection" />
<link rel="stylesheet" href="<c:url value='/resources/css/addressbook.css'/>" type="text/css" media="screen, projection" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    var max = 2;
    var checkboxes = $('input[type="checkbox"]');
                       
    checkboxes.change(function(){
        var current = checkboxes.filter(':checked').length;
        checkboxes.filter(':not(:checked)').prop('disabled', current >= max);
    });
    
});
</script>
</head>
<body>
<div class="container_12">
    <h2>Address Book</h2>
    <c:if test="${not empty errors.allErrors }">
      <div class="gid_12">
         <div class="error">
             <ul>
                 <c:forEach items="${errors.globalErrors}" var="error">
                     <li><spring:message code="${error.code}.${error.objectName}" /></li>
                 </c:forEach>
                 <c:forEach items="${errors.fieldErrors}" var="error">
                     <li><spring:message code="${error.code}.${error.objectName}.${error.field}" /></li>
                 </c:forEach>
             </ul>
         </div>
        </div>
    </c:if>
    <div class="clear"></div>
    <hr />
    <c:if test="${not empty addressBookList }">
       <div class="grid_3">
           <p><b>Address Books</b></p>
           <ul>
               <c:url value="/addressbook/compare" var="compareUrl"></c:url>
               <form:form commandName="compareAddressBook" action="${compareUrl}" method="GET">             
               <c:forEach items="${addressBookList}" var="addressBook">
                   <c:url value="/addressbook/${addressBook.id}" var="addressBookUrl"></c:url>
                   <c:choose>
                       <c:when test="${selectedAddressBook.id == addressBook.id}">
                           <li class="selected"><form:checkbox path="addressBook" value="${addressBook.id}" /><a href="${addressBookUrl}">${addressBook.name}</a></li>
                       </c:when>
                       <c:otherwise>
                           <li><form:checkbox path="addressBook" value="${addressBook.id}" /><a href="${addressBookUrl}">${addressBook.name}</a></li>
                       </c:otherwise>
                   </c:choose>         
               </c:forEach>
               <form:button value="compare">Compare</form:button>
               </form:form>
           </ul>
           <hr/>
           <p><b>New Address Book</b></p>
           <form:form commandName="newAddressBook" action="${action}" method="POST">
                <fieldset>
                    <form:input path="name" type="text" cssErrorClass="errorField" />                
                    <form:button value="create">Create</form:button>
                </fieldset>
           </form:form>
       </div>        
       <div class="grid_9">
           <jsp:doBody></jsp:doBody>
       </div>
   </c:if>
   <c:if test="${empty addressBookList}">
        <div class="grid_4 prefix_4 suffix_5">
            <p>You have no address book, please enter one.</p>
            <form:form commandName="newAddressBook" action="${action}" method="POST">
                <fieldset>
                    <form:label path="name">Name</form:label>
                    <form:input path="name" type="text" cssErrorClass="errorField" />
                    <form:button value="create">Create</form:button>
                </fieldset>
            </form:form>
        </div>
   </c:if>
</div>
</body>
</html>

