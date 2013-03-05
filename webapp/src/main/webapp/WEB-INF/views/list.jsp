<%@ include file="../include/includes.jsp"%>
<a:page>
     <c:choose>     
	     <c:when test="${not empty selectedAddressBook}">
		     <c:url value="/addAddressee" var="addAddress"></c:url>
			 <form:form commandName="newAddressee" action="${addAddress}" method="POST">
			     <fieldset>
			            <input type="hidden" value="${selectedAddressBook.id}" id="addressBookId" name="addressBookId"/>
			         <form:label path="name">Name</form:label>
			         <form:input path="name" type="text" cssErrorClass="errorField" />                
			            <form:label path="phone">Phone</form:label>
			            <form:input path="phone" type="text" cssErrorClass="errorField" />                
			         <form:button value="ADD">Add</form:button>
			     </fieldset>
			 </form:form>
			 <c:choose>
			    <c:when test="${not empty selectedAddressBook.addressees}">
			        <c:forEach items="${selectedAddressBook.addressees}" var="addressee">
			            <p>Name: ${addressee.name} Phone: ${addressee.phone}</p>
			        </c:forEach>
			    </c:when>
			    <c:otherwise>
			        <p>There have been no friends added to this address book</p>
			    </c:otherwise>
			 </c:choose>
		 </c:when>
		 <c:otherwise>
		      <p><b>Please select a address book from the list to the left</b></p>
		 </c:otherwise>
     </c:choose>
	 
</a:page>
