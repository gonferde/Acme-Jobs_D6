<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="worker.application.list.label.referenceNumber" path="referenceNumber" width="16%"/>
	<acme:list-column code="worker.application.list.label.creationMoment" path="creationMoment" width="16%"/>
	<acme:list-column code="worker.application.list.label.status" path="status" width="16%"/>
	<acme:list-column code="worker.application.list.label.statement" path="statement" width="16%"/>
	<acme:list-column code="worker.application.list.label.skills" path="skills" width="16%"/>
	<acme:list-column code="worker.application.list.label.qualifications" path="qualifications" width="16%"/>
</acme:list>