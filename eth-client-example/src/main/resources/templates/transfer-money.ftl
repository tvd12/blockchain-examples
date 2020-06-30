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
							<br />
							<form name="transfer" action="transfer" method="POST">
							  <div class="form-group row">
							    <label for="inputPassword" class="col-sm-2 col-form-label">From password:</label>
							    <div class="col-sm-10">
							      <input name="fromPassword" type="text" class="form-control" id="inputPassword" placeholder="Password" style="width: 30%">
							    </div>
							  </div>
							  <div class="form-group row">
							    <label for="inputPassword" class="col-sm-2 col-form-label">From wallet file:</label>
							    <div class="col-sm-10">
							      <input name="fromWalletFile" type="text" class="form-control" id="inputPassword" placeholder="" style="width: 50%">
							    </div>
							  </div>
							  <div class="form-group row">
							    <label for="staticEmail" class="col-sm-2 col-form-label">From:</label>
							    <div class="col-sm-10">
							      <input name="from" type="text" class="form-control-plaintext" value="" style="width: 50%">
							    </div>
							  </div>
							  <div class="form-group row">
							    <label for="to" class="col-sm-2 col-form-label">To:</label>
							    <div class="col-sm-10">
							      <input name="to" type="text" class="form-control-plaintext" id="to" value="" style="width: 50%">
							    </div>
							  </div>
							  <div class="form-group row">
							    <label for="value" class="col-sm-2 col-form-label">Value:</label>
							    <div class="col-sm-10">
							      <input name="value" type="text" class="form-control-plaintext" id="value" value="">
							    </div>
							  </div>
							  <input type="submit" value="Submit" class="btn btn-success" />
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
									<h5>Updated Accounts</h5>
								</div>
							</div>
						</div>
						<div>
							<p>
								
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
		<!--<#include "shared/footer.ftl">-->
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