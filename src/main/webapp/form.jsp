<!DOCTYPE html>
<html>
    <head>
        <title>YakShop-Order</title>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="assets/styles.css" rel="stylesheet" media="screen">
        <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>
    
    <body>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#">YakShop</a>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span3" id="sidebar">
                    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                        <li>
                            <a href="/yak"><i class="icon-chevron-right"></i> Dashboard</a>
                        </li>
                        <li >
                            <a href="/yak/calendar.jsp"><i class="icon-chevron-right"></i> Calendar</a>
                         </li>
                        <li class="active">    
                        	<a href="/yak/form.jsp"><i class="icon-chevron-right"></i> Order</a>
                        </li>
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
                      <!-- morris stacked chart -->
                                      <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">Order Enquiry</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12">
                                    <form class="form-horizontal"  action="" id="orderEnqForm" method="POST">
                                      <fieldset>
                                        <legend>YakShop Order Enquiry </legend>
                                        <div class="control-group">
                                          <label class="control-label" for="typeahead">Customer Name </label>
                                          <div class="controls">
                                            <input type="text" name="customer"  class="span6" id="customerName" >
                                            <p class="help-block"><label for="customerName">required, at least 2 characters</label></p>
                                          </div>
                                        </div>
                                        <div class="control-group">
                                          <label class="control-label" for="typeahead">Milk Qty Required</label>
                                          <div class="controls">
                                            <input type="text" class="span6" id="milkReq" >
                                            <p class="help-block"><label for="milkReq">Milk Qty Required</label></p>
                                          </div>
                                        </div>
                                        <div class="control-group">
                                          <label class="control-label" for="typeahead">Skin Qty Required </label>
                                          <div class="controls">
                                            <input type="text" class="span6" id="skinReq" >
                                            <p class="help-block"><label for="skinReq">Skin Qty Required</label></p>
                                          </div>
                                        </div>
                                        <div class="control-group">
                                          <label class="control-label" for="typeahead">Required Day </label>
                                          <div class="controls">
                                            <input type="text" class="span6" id="dayReq" >
                                            <p class="help-block"> <label for="dayReq">Required Day</label></p>
                                          </div>
                                        </div>
                                        <div class="form-actions">
                                          <button  id="btn_order" class="btn btn-primary">Save changes</button>
                                          <button type="reset" class="btn">Cancel</button>
                                        </div>
                                      </fieldset>
                                    </form>

                                </div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>
                </div>
            </div>
            <hr>
            <footer>
                <p>&copy; Vutphala 2014</p>
            </footer>
        </div>
        <!--/.fluid-container-->
    
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/additional-methods.min.js"></script>
    
        <script src="assets/scripts.js"></script>
        <script>
        $(document).ready(function () {
        
        	var validator=$("#orderEnqForm").validate({
    			errorClass:'err-form err-msg',
    			errorElement:'label',
    			submitHandler: function(form) { 
    				/*$.post('/yak/order/'+$('#dayReq').val(),'{"customer":"'+$('#customerName').val()+'", "order":{ "milk":"'+$('#milkReq').val()+'"}}' ,  
    					function(data) {
    						if(data.CODE=='200'){
    							//alert("Sucess");
    							$(".contact-form-data-group").hide();
    							$(".contact-form-message-group").show();
    							//$(this).hide();
    							
    							
    						}else{
    						//	alert("invalid captcha");
    						}
    		        // callback   
    					}
    				);*/
    				$.ajax({
    					  url:'/yak/order/'+$('#dayReq').val(),
    					  type:"POST",
    					  data:'{"customer":"'+$('#customerName').val()+'", "order":{ "milk":"'+$('#milkReq').val()+'","skins":"'+$('#skinReq').val()+'"}}',
    					  contentType:"application/json; charset=utf-8",
    					  dataType:"json",
    					  success: function(){
    					    
    					  }
    					});
    				return false; // required to block normal submit since you used ajax
    			}
    			 
    			});
        
        	$( "#customerName" ).rules( "add", {
				required: true,
				minlength: 2,
				messages: {
					required: "Please enter your name",
					minlength: "Please enter your name"
					}
			});

        	$( "#dayReq" ).rules( "add", {
				required: true,
				number:true,
				messages: {
					required: "Please enter your Order Enquiry for the Day",
					minlength: "Please enter your Order Enquiry for the Day"
					}
			});
        	$( "#milkReq" ).rules( "add", {
				required: true,
				number:true,
				messages: {
					required: "Please enter your Order Enquiry for the Day",
					minlength: "Please enter your Order Enquiry for the Day"
					}
			});
        });
        </script>
    </body>

</html>
