<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	
	<acme:list-column code="authenticated.announcement.list.label.moment" path="moment" width="25%"/>
	<acme:list-column code="authenticated.announcement.list.label.title" path="title" width="25%"/>
	<acme:list-column code="authenticated.announcement.list.label.moreInfo" path="moreInfo" width="25%"/>
	<acme:list-column code="authenticated.announcement.list.label.text" path="text" width="25%"/>

</acme:list>