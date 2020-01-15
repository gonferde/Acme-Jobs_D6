<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="employer.duty.list.label.title" path="title" width="40%"/>
	<acme:list-column code="employer.duty.list.label.description" path="description" width="30%"/>
	<acme:list-column code="employer.duty.list.label.percentageTime" path="percentageTime" width="30%"/>
</acme:list>