<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.job.list.label.referenceNumber" path="referenceNumber" width="16%"/>
	<acme:list-column code="authenticated.job.list.label.status" path="status" width="16%"/>
	<acme:list-column code="authenticated.job.list.label.title" path="title" width="16%"/>
	<acme:list-column code="authenticated.job.list.label.deadline" path="deadline" width="16%"/>
	<acme:list-column code="authenticated.job.list.label.salary" path="salary" width="16%"/>
	<acme:list-column code="authenticated.job.list.label.moreInfo" path="moreInfo" width="16%"/>
</acme:list>