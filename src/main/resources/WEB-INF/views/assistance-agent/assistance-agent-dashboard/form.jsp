<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:print code="assistanceAgent.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:print code="assistanceAgent.dashboard.form.label.resolved-claims"/>
		</th>
		<td>
			<acme:print value="${resolvedClaims}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:print code="assistanceAgent.dashboard.form.label.rejected-claims"/>
		</th>
		<td>
			<acme:print value="${rejectedClaims}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:print code="assistanceAgent.dashboard.form.label.top-three-months"/>
		</th>
		<td>
			<acme:print value="${topThreeMonths}"/>
		</td>
	</tr>	
</table>

<h2>
    <acme:print code="assistanceAgent.dashboard.form.title.log-statistics-indicators"/>
</h2>

<table class="table table-sm">
    <tr>
        <th scope="row">
            <acme:print code="assistanceAgent.dashboard.form.label.claim-logs-statistics-average"/>
        </th>
        <td>
            <acme:print value="${logsStatistics.average}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:print code="assistanceAgent.dashboard.form.label.claim-logs-statistics-minimum"/>
        </th>
        <td>
            <acme:print value="${logsStatistics.minimum}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:print code="assistanceAgent.dashboard.form.label.claim-logs-statistics-maximum"/>
        </th>
        <td>
            <acme:print value="${logsStatistics.maximum}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:print code="assistanceAgent.dashboard.form.label.claim-logs-statistics-deviation"/>
        </th>
        <td>
            <acme:print value="${logsStatistics.deviation}"/>
        </td>
    </tr>
</table>

<h2>
    <acme:print code="assistanceAgent.dashboard.form.title.log-statistics-month-indicators"/>
</h2>

<table class="table table-sm">
    <tr>
        <th scope="row">
            <acme:print code="assistanceAgent.dashboard.form.label.claim-logs-statistics-average"/>
        </th>
        <td>
            <acme:print value="${assistedStatistics.average}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:print code="assistanceAgent.dashboard.form.label.claim-logs-statistics-minimum"/>
        </th>
        <td>
            <acme:print value="${assistedStatistics.minimum}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:print code="assistanceAgent.dashboard.form.label.claim-logs-statistics-maximum"/>
        </th>
        <td>
            <acme:print value="${assistedStatistics.maximum}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:print code="assistanceAgent.dashboard.form.label.claim-logs-statistics-deviation"/>
        </th>
        <td>
            <acme:print value="${assistedStatistics.deviation}"/>
        </td>
    </tr>
</table>

<acme:return/>