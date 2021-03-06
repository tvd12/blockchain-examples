<#import "spring.ftl" as spring />

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ECoin Admin Tools</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="font-awesome/css/font-awesome.css" rel="stylesheet">

<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<!-- Custom -->
<link href="css/custom/dashboard.css" rel="stylesheet">
<!-- c3 Charts -->
<link href="css/plugins/c3/c3.min.css" rel="stylesheet">
<style>
#title {
	padding: 0px;
}
</style>
</head>

<body>

	<div id="wrapper">
		<#include "shared/navigation.ftl">
		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top white-bg" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="#"> <i class="fa fa-bars"></i>
						</a>
						<form role="search" class="navbar-form-custom" method="post"
							action="#">
							<div class="form-group"></div>
						</form>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li><a href="#"> <i class="fa fa-sign-out"></i>Logout
						</a></li>
					</ul>
				</nav>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<div class="row m-b-sm m-t-sm">
								<div class="col-md-3">
									<h5>Create Account</h5>
								</div>
							</div>
						</div>
						<div>
							<form name="account" action="create-account" method="POST">
								<p>Password: </p>
								<input type="text" name="password" />
								<input type="submit" value="Submit" />
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<div class="row m-b-sm m-t-sm">
								<div class="col-md-3">
									<h5>Newest Account</h5>
								</div>
							</div>
						</div>
						<div>
							<p>
								${createdAccount}
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<div class="row m-b-sm m-t-sm">
								<div class="col-md-3">
									<h5>Account List</h5>
								</div>
							</div>
						</div>
						<div>
							<table class="table">
								<tr>
									<th>index</th>
									<th>Address</th>
									<th>Balance</th>
								</tr>
								<#list accounts?keys as key>
								<tr>
									<td>${key?index + 1}</td>
									<td>${accounts[key].address}</td>
									<td>${accounts[key].eth}</td>
								</tr>
								</#list>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<#include "shared/footer.ftl">
	</div>
	<!-- Mainly scripts -->
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!-- Custom and plugin javascript -->
	<script src="js/inspinia.js"></script>
	<script src="js/plugins/pace/pace.min.js"></script>
	<!-- ChartJs -->
	<script src="js/plugins/chartJs/Chart.min.js"></script>
	<!-- Flot -->
	<script src="js/plugins/flot/jquery.flot.js"></script>
	<script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
	<script src="js/plugins/flot/jquery.flot.resize.js"></script>
	<script src="js/plugins/flot/jquery.flot.pie.js"></script>
	<script src="js/plugins/flot/jquery.flot.time.js"></script>
</body>

</html>