<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="background: #E0E0E0; height: 113px; padding: 5px;">
	<div style="float: left">
		<img height="100px" width="100px" src="static/images/ems.gif" />
		<div align="middle">
			<a
				href="edit_user?id=${sessionScope['scopedTarget.loggedInUser'].getUser().id}"><c:out
					value="${sessionScope['scopedTarget.loggedInUser'].getUser().name}" />
			</a>
		</div>
	</div>
</div>