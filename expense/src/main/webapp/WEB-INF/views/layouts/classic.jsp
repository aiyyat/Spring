<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<!doctype html>
<link
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<style>
table {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #aeaeae;
}

table td {
	border-width: 1px;
	padding: 5px;
	border-style: solid;
}

#filler tr:hover {
	background-color: #a5e4e4
}

.ui-menu {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	font-style: normal;
	font-variant: normal;
	font-weight: 500;
	line-height: 26.4px;
}
</style>
</head>
<body>
<head>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<table width="100%" border="0">
		<tr>
			<td colspan="2"><tiles:insertAttribute name="header" /></td>
		</tr>
		<tr>
			<td width="10%" style="padding: 5px; vertical-align: top;"><tiles:insertAttribute
					name="menu" /></td>
			<td width="80%" style="padding: 5px;"><tiles:insertAttribute
					name="body" /></td>
		</tr>
		<tr>
			<td colspan="2"><tiles:insertAttribute name="footer" /></td>
		</tr>
	</table>
</body>
</html>