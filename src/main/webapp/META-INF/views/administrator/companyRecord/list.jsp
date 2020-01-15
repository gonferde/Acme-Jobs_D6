<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>

	<acme:list-column code="administrator.companyRecord.list.label.companyName" path="companyName" width="11%"/>
	<acme:list-column code="administrator.companyRecord.list.label.sector" path="sector" width="11%"/>
	<acme:list-column code="administrator.companyRecord.list.label.ceo" path="ceo" width="11%"/>
	<acme:list-column code="administrator.companyRecord.list.label.description" path="description" width="11%"/>
	<acme:list-column code="administrator.companyRecord.list.label.url" path="url" width="11%"/>
	<acme:list-column code="administrator.companyRecord.list.label.phone" path="phone" width="11%"/>
	<acme:list-column code="administrator.companyRecord.list.label.email" path="email" width="11%"/>
	<acme:list-column code="administrator.companyRecord.list.label.incorporated" path="incorporated" width="11%"/>
	<acme:list-column code="administrator.companyRecord.list.label.stars" path="stars" width="11%"/>

</acme:list>