<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox placeholder="acme.customer.form.identifier.placeholder" code="authenticated.customer.form.label.identifier" path="identifier"/>
	<acme:input-textbox placeholder="acme.customer.form.phoneNumber.placeholder" code="authenticated.customer.form.label.phoneNumber" path="phoneNumber"/>
	<acme:input-textbox code="authenticated.customer.form.label.address" path="address"/>
	<acme:input-textbox code="authenticated.customer.form.label.city" path="city"/>
	<acme:input-textbox code="authenticated.customer.form.label.country" path="country"/>
	
	<jstl:if test="${_command == 'create'}"> 
		<acme:submit  code="authenticated.consumer.form.button.create" action="/authenticated/customer/create"/>
	</jstl:if>
	<jstl:if test="${_command == 'update'}">
		<acme:submit code="authenticated.consumer.form.button.update" action="/authenticated/customer/update"/>
	</jstl:if>
</acme:form>
