<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code = "flight-crew-member.flight-assignment.list.label.scheduledDeparture" path= "scheduledDeparture"/>
	<acme:list-column code = "flight-crew-member.flight-assignment.list.label.flight-number" path= "flightNumber"/>
	<acme:list-column code = "flight-crew-member.flight-assignment.list.label.duty" path= "duty"/>
	<acme:list-column code = "flight-crew-member.flight-assignment.list.label.status" path= "status"/>
	
	<acme:list-payload path="payload"/>
</acme:list>