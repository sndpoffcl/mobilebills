<html>
<head>
<style>
body {
  background-color: #ECECEC;
  color: #444444;
  padding: 0;
  margin: 0;
  perspective: 1px;
  transform-style: preserve-3d;
  height: 100%;
  overflow-x: hidden;
  font-family: 'Roboto';
}
.left-underline {
  display: inline-block;
  vertical-align: middle;
  -webkit-transform: translateZ(0);
  transform: translateZ(0);
  box-shadow: 0 0 1px rgba(0, 0, 0, 0);
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  -moz-osx-font-smoothing: grayscale;
  position: relative;
  overflow: hidden;
}
.left-underline:before {
  content: "";
  position: absolute;
  z-index: -1;
  left: 0;
  right: 100%;
  bottom: 0;
  background: #ECECEC;
  height: 4px;
  -webkit-transition-property: right;
  transition-property: right;
  -webkit-transition-duration: 0.15s;
  transition-duration: 0.15s;
  -webkit-transition-timing-function: ease-out;
  transition-timing-function: ease-out;
}
.left-underline:hover:before, .left-underline:focus:before, .left-underline:active:before {
  right: 0;
}
div.navbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background: #ee6e73;
  -webkit-box-shadow: 0px 2px 6px 0px rgba(50, 50, 50, 0.75);
  -moz-box-shadow:    0px 2px 6px 0px rgba(50, 50, 50, 0.75);
  box-shadow:         0px 2px 6px 0px rgba(50, 50, 50, 0.75);
  z-index: 9999999;
}
div.navbar > ul.navbar-container {
  list-style-type: none;
  margin: 0;
  padding-left: 1em;
  padding-right: 48em;
  overflow: hidden;
}
div.navbar > ul > li.nav-item {
  float: right;
  background-color: rgba(0, 0, 0, 0.1);
  transition: all 0.5s ease;
}
div.navbar > ul > li.active {
  background-color: rgba(0, 0, 0, 0.18);
}
div.navbar > ul > li:hover {
  background-color: rgba(0, 0, 0, 0.2);
}
a.nav-button {
  font-family: 'Roboto';
  text-decoration: none;
  line-height: 60px;
  padding-left: 00px;
  padding-right: 00px;
  color: rgba(228, 241, 254, 1);
  transition: all 0.5s ease;
}
a.nav-button:hover {
  text-decoration: none;
}
li.active > a.nav-button {
  cursor: default;
}
.brand-logo {
  float: left;
  cursor: default;
}
hgroup{
  display: inline-block;
  text-align: center;
  position: relative;
  top: 80%;
  left: 20%;
  transform: translateX(-50%) translateY(-50%);
  color: #fff;
  border: 5px solid #fff;
  padding: .5em 3em;
  background-color: rgba(0,0,0,.2);
  z-index: 2;
}
</style>
meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Add icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}
.input-container {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  width: 100%;
  margin-bottom: 15px;
  position:relative;
  left:250px;
  bottom:500px;
}
.icon {
  padding: 20px;
  background: #ee6e73;
  color: white;
  min-width: 50px;
  text-align: center;
}
.input-field {
  width: 100%;
  padding: 10px;
  outline: none;
}
.input-field:focus {
  border: 2px solid #ee6e73;
}
/* Set a style for the submit button */
.btn {
  background-color: #ee6e73;
  color: white;
  padding: 15px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
  position:relative;
  left:250px;
  bottom:500px;
  
}
.btn:hover {
  opacity: 1;
}
.dropdown {
  float: left;
  overflow: hidden;
}

.dropdown .dropbtn {
  font-size: 16px;  
  border: none;
  outline: none;
    line-height: 35px;
  color: white;
  padding: 14px 18px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.navbar a:hover, .dropdown:hover .dropbtn {
  background-color: black;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 10px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  float: none;
  color: black;
  padding: 8px 14px;
  text-decoration: none;
  display: block;
  text-align: center;
}

.dropdown-content a:hover {
  background-color: #ddd;
}

.dropdown:hover .dropdown-content {
  display: block;
}
</style>
</head>
<body>

   <link href='https://fonts.googleapis.com/css?family=Roboto:400,300' rel='stylesheet' type='text/css'>
    <div class="navbar">
      <ul class="navbar-container">
        <li><a href="#" class="left-underline nav-button brand-logo">Air Voice</a></li>
        <li class="nav-item"><a href="#section-3" class="left-underline nav-button">Join</a></li>
      <div class="dropdown">
    <button class="dropbtn">Plan
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="getCreatePlan">Create Plan</a>
      <a href="getChangePlan">Change Plan</a>
      <a href="getPlanDetails">Show Plan Details</a>
	  <a href="showAllPlanDetails">Show All Plans</a>
    </div>
  </div> 
    		<div class="dropdown">
    <button class="dropbtn">Postpaid
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="openAccount">Open Postpaid Account</a>
      <a href="getPostpaidDetails">Single Postpaid Details</a>
      <a href="getAllPostpaidAccounts">All Postpaid Details</a>
    </div>
  </div> 
		<div class="dropdown">
    <button class="dropbtn">Bill
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="getGenerateBill">Generate Bill</a>
      <a href="getBillDetails">Show Bill</a>
      <a href="getAllBillDetails">Show All Bill</a>
    </div>
  </div> 
  <div class="dropdown">
    <button class="dropbtn">Customer
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="addCustomer">Add New Customer</a>
      <a href="findCustomer">Show Customer Details</a>
      <a href="getRemoveCustomer">Remove Customer</a>
      <a href="allCustomers">Show All Customers Details</a>
    </div>
  </div> 
    </div>
	<h2><font color="#ee6e73">join us! Talk more with your beloved.</h2>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<div class="parallax p1" id="section-1" position="relative" top="100px">
      <hgroup>
        <h1>Welcome</h1>
        <h2>To Air Voice.</h2>
      </hgroup>
    </div>
	<br><br><br>
	<div class="parallax p1" id="section-1">
      <hgroup>
        <h1>Total Bill is</h1>
        <h2>${amount}</h2>
      </hgroup>
    </div>
	</body>
	</html>