<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form >
	<acme:form-textbox code="employer.application.form.label.referenceNumber" path="referenceNumber" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.creationMoment" path="creationMoment" readonly="true" />
	<acme:form-select code="employer.application.form.label.status" path="status" >
		<acme:form-option code="employer.application.form.label.status.accept" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:form-option code="employer.application.form.label.status.reject" value="REJECTED" selected="${status == 'REJECTED'}"/>
	<!-- PARA PODER DEJAR EL STATUS TAMBIEN EN PENDING -->
		<acme:form-option code= "employer.application.form.label.status.equal" value="PENDING" selected="${status == 'PENDING'}"/> 
	</acme:form-select>
	<jstl:if test="${command == 'show' || status == 'REJECTED'}">
		<acme:form-textarea code="employer.application.form.label.RejectedJustification" path="RejectedJustification" />
	</jstl:if>
	<acme:form-textbox code="employer.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.skills" path="skills" readonly="true" />
	<acme:form-textbox code="employer.application.form.label.qualifications" path="qualifications" readonly="true" />

	<acme:form-submit test="${command == 'show'}"
		code="employer.application.form.button.update"
		action="/employer/application/update"/>	
	<acme:form-submit test="${command == 'update'}"
		code="employer.application.form.button.update"
		action="/employer/application/update"/>	
	<acme:form-return code="employer.application.form.button.return"/>

	
</acme:form>
