<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command == 'show'}">
	<acme:form-textbox code="worker.application.form.label.referenceNumber" path="referenceNumber" readonly="true"/>
	<acme:form-moment code="worker.application.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-textbox code="worker.application.form.label.status" path="status" readonly="true"/>
	<acme:form-textarea code="worker.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-textbox code="worker.application.form.label.skills" path="skills" readonly="true"/>
	<acme:form-textbox code="worker.application.form.label.qualifications" path="qualifications" readonly="true"/>
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/job/show?id=${job.id}')"
		class="btn btn-default">
		<acme:message code="worker.application.form.label.jobButton" />
	</button>
		</jstl:if>	
	
	<jstl:if test="${command == 'create'}">
		<acme:form-textbox code="worker.application.form.label.referenceNumber" path="referenceNumber" placeholder="AAAA-AAAA:WWWW"/>
		<acme:form-textarea code="worker.application.form.label.statement" path="statement"/>
		<acme:form-textarea code="worker.application.form.label.skills" path="skills"/>
		<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications"/>
		<acme:form-submit code="worker.application.form.button.create" action="/worker/application/create?idJob=${idJob}" method="post"/>
	</jstl:if>
		
	<acme:form-return code="worker.application.form.button.return"/>
	
	
</acme:form>