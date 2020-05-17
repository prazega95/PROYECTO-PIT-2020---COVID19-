/**********************************************************/
/*********************CARGA PAGUINAS***********************/
/**********************************************************/
function JsHtmlMantenimientoAuto() {
	cargaHtml("http://localhost:8080/Mecanica/ui/crudAuto.jsf");
}

function JsHtmlMantenimientoProveedor() {
	cargaHtml("http://localhost:8080/Mecanica/ui/crudProveedor.jsf");
}


function JsHtmlMantenimientoItem() {
	cargaHtml("http://localhost:8080/Mecanica/ui/crudItem.jsf");
}

function JsHtmlMantenimientoMarca() {
	cargaHtml("http://localhost:8080/Mecanica/ui/crudMarca.jsf");
}

function JsHtmlMantenimientoModelo() {
	cargaHtml("http://localhost:8080/Mecanica/ui/crudModelo.jsf");
}

function JsHtmlMantenimientoEmpleado() {
	cargaHtml("http://localhost:8080/Mecanica/ui/crudEmpleado.jsf");
}

function JsHtmlMantenimientoCliente() {
	cargaHtml("http://localhost:8080/Mecanica/ui/crudCliente.jsf");
}

function JsHtmlMantenimientoFichaTecnica() {
	cargaHtml("http://localhost:8080/Mecanica/ui/fichaTecnica.jsf");
}
function JsHtmlMantenimientoFichaTecnica2() {
	cargaHtml("http://localhost:8080/Mecanica/ui/fichaTecnica2.jsf");
}


function cargaHtml(html){
	$.get(html, function(data) {
		limpiaContent();
	    
	    $("#CONTENTSIDE").html(data);
	}).fail(function(x,z,y){
		if(y=="No Encontrado"){
			$.get("http://localhost:8080/Mecanica/error/404.jsf", function(data) {
			    $("#CONTENTSIDE").empty();
			    $("#CONTENTSIDE").html(data);
			});
		}
	});
}

function limpiaContent(){
	$("#CONTENTSIDE").empty();
    
    //LIMPIA
    $('#textarea_simulator').remove();
    $('.ui-menu.ui-menu-dynamic.contextTable').remove();
    $('.ui-dialog-docking-zone').remove();
    $('.ui-growl.ui-widget').remove();
    $('.ui-selectonemenu-panel').remove();
    $('.ui-menu.ui-menu-dynamic').remove();
}

/***********************************************************/
/*******************EVENTOS GENERALES***********************/
/***********************************************************/

//$(document)
//.off('click','.ui-widget-content.ui-datatable-selectable')
//.on('click','.ui-widget-content.ui-datatable-selectable',function(){
//	$(this).siblings().removeClass('ui-state-highlight');
//	$(this).addClass('ui-state-highlight');
//});
//
//$(document)
//.off('contextmenu','.ui-widget-content.ui-datatable-selectable')
//.on('contextmenu','.ui-widget-content.ui-datatable-selectable',function(){
//	$(this).siblings().removeClass('ui-state-highlight');
//	$(this).addClass('ui-state-highlight');
//});