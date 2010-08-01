//FUNCION QUE SE LLAMA AL HACER CLICK EN UN NODO: ES NECESARIO QUE SE IMPLEMENTE EN EL CLIENTE LA FUNCION selecNodoImpl
var tf_msgDesplegar = "Desplegar";
var tf_msgReplegar = "Replegar";
var tf_suffixNode = "mn_";
var tf_suffixImageNode = "iA_";

function seleccionarNodo(idArbol, nodo) {
	deSeleccionarArbol(idArbol);
	nodo.style.backgroundColor = "#CCCCCC";
}
function seleccionarNodoById(idArbol, idNodo) {
	if ((idArbol != '') && (idNodo != '')) {
		deSeleccionarArbol(idArbol);
		id = idArbol + "_" + idNodo;
		var obj;
		seleccionado = false;
		var allHTMLTags = document.getElementsByTagName("span");
		for (i = 0; i != allHTMLTags.length && !seleccionado; i++) {
			if (allHTMLTags[i].className == id) {
				allHTMLTags[i].style.backgroundColor = "#CCCCCC";
				obj = allHTMLTags[i];
				seleccionado = true;
			}
		}

		abrirRama(obj);
	}
}
function deSeleccionarArbol(idArbol) {
	var allHTMLTags = document.getElementsByTagName("span");
	for (i = 0; i != allHTMLTags.length; i++) {
		if (allHTMLTags[i].className.lastIndexOf(idArbol) != -1) {
			allHTMLTags[i].style.backgroundColor = "";
		}
	}
}
function deshabilitarRamas(readOnly) {
	if (readOnly == 'true') {
		var allHTMLTags = document.getElementsByTagName("img");
		for (i = 0; i != allHTMLTags.length; i++) {
			if (allHTMLTags[i].src.indexOf("plus") != -1) {
				allHTMLTags[i].onclick = "";
			}
		}
	}
}
function abrirRama(obj) {
	if (obj != undefined) {
		var padre = obj.parentNode;
		while (padre.className != 'dtree') {
			if (padre.id != null && padre.id.indexOf('TOGGLE_SPAN') != -1
					&& cambiarImagenNodo(padre.id)) {
				treeNavClick(padre.id, '', '', '', '', '', '', '', '');
			}
			padre = padre.parentNode;
		}
	}
}
function cambiarImagenNodo(id) {
	var allHTMLTags = document.getElementsByTagName("img");
	cambiada = false;
	for (i = 0; i != allHTMLTags.length && !cambiada; i++) {
		if (allHTMLTags[i].onclick != null) {
			onclick = allHTMLTags[i].onclick;
			img = allHTMLTags[i].src;
			if (onclick.indexOf(id) != -1 && img.indexOf("plus") != -1) {
				allHTMLTags[i].src = img.replace('plus', 'minus');
				cambiada = true;
			}
		}
	}
	return cambiada
}
function selecNodo(cod, desc, esHoja) {
	if (typeof (selecNodoImpl) != 'undefined')
		selecNodoImpl(cod, desc, esHoja)
}
function overNodo(txt) {
	window.status = txt
	return true
}
function clickNodo(nomBase) {
	// Desplegar/replegar subarbol
	var srcImagen
	var obj = document.getElementById(tf_suffixNode + nomBase)
	if (obj != null) {
		estilo = obj.style.display
		obj.style.display = (estilo == "block") ? "none" : "block"
	}

	// Cambiar imagen mas/menos
	obj = document.getElementById(tf_suffixImageNode + nomBase)
	if (obj != null) {
		srcImagen = obj.src
		srcImagen = srcImagen.substring(0, srcImagen.length - 4)
		obj.src = srcImagen.substring(0, srcImagen.length - 3)
				+ ((srcImagen.indexOf("iMa") == -1) ? "iMa.gif" : "iMe.gif")
		obj.title = (srcImagen.indexOf("Ma") == -1) ? tf_msgDesplegar
				: tf_msgReplegar
	}

	if (typeof postClickNodoSpecialized != 'undefined')
		postClickNodoSpecialized(nomBase)
}
function tf_expandirTodos() {
	tf_expandRepleAux("none")
}
function tf_replegarTodos() {
	tf_expandRepleAux("block")
}
function tf_expandRepleAux(value) {
	var capas = document.getElementsByTagName("div")
	var capa
	for ( var i = 0; i < capas.length; i++) {
		capa = capas[i]
		if (capa.id.indexOf(tf_suffixNode) == 0) {
			if (capa.style.display == value || capa.style.display == '')
				clickNodo(capa.id.substring(tf_suffixNode.length))
		}
	}
}
function tf_deseleccionarTodos(formName) {
	var f = eval("document." + formName)
	for (i = 0; i < f.elements.length; i++) {
		if (f.elements[i].type == "checkbox")
			f.elements[i].checked = 0
	}
}

var ultimoNodoResaltado = null;
function resaltaColorNodo(nomBase) {
	// Restauramos al color original la descripcion del ultimo nodo clickeado.
	var obj = document.getElementById(ultimoNodoResaltado);
	if (obj)
		obj.className = 'treeNode';
	// Asignamos el color a la descripcion del nodo seleccionado.
	obj = document.getElementById("row_" + nomBase);
	if (obj) {
		obj.className = 'treeNodeSelected';
		ultimoNodoResaltado = "row_" + nomBase;
	}
}

if (typeof postClickNodoSpecialized != 'undefined') {
	postClickNodoSpecialized(nomBase)
}

function mover() {
	window.moveTo(0, 0);
}

function MM_openBrWindow(theURL, winName, features) { // v2.0
	window.open(theURL, winName, features);
}

// Seleccionar listado
var opcion = 1;
var msjSelec = 0;
function seleccionarTodo(total, formulario, fila, boton, chk) {

	var btn1 = boton + "1";
	btn1 = document.getElementById(btn1);
	estilo = btn1.style.display;
	btn1.style.display
	if (btn1.style.display == "") { // opcion == 1) {
		opcion = 0;
		marcarTodasFilasCheck(total, formulario, fila, chk);
	} else {
		opcion = 1;
		desmarcarTodasFilasCheck(total, formulario, fila, chk);
	}
	if (msjSelec == 0) {
		alert('Se han seleccionado todos los registros');
		msjSelec = 1;
	} else {
		alert('Se han deseleccionado todos los registros');
		msjSelec = 0;
	}
	var btn1 = boton + "1";
	btn1 = document.getElementById(btn1);
	estilo = btn1.style.display;
	btn1.style.display = (estilo == "") ? "none" : ""
	var btn2 = boton + "2";
	btn2 = document.getElementById(btn2);
	estilo = btn2.style.display;
	btn2.style.display = (estilo == "") ? "none" : ""
}
var opcion2 = 1;
function seleccionarListado(total, formulario, fila, chk) {

	if (opcion2 == 1) {
		opcion2 = 0;
		marcarTodasFilasCheck(total, formulario, fila, chk);

	} else {
		opcion2 = 1;
		desmarcarTodasFilasCheck(total, formulario, fila, chk);
	}
}
function pestana(divId, num, name) {
	pestana(divId, num, name, null);
}

function pestana(divId, num, name, saveInputId) {
	var divId2 = name + divId;

	var node = null;
	var tag = null;
	var classElements08 = new Array();
	if (node == null)
		node = document;
	if (tag == null)
		tag = '*';
	var els = node.getElementsByTagName(tag);
	var elsLen = els.length;
	var pattern = new RegExp('(^|\\\\s)' + divId2 + '(\\\\s|$)');
	for (i = 0, j = 0; i < elsLen; i++) {
		if (pattern.test(els[i].className)) {
			classElements08[j] = els[i];
			j++;
		}
	}
	if (classElements08[0] != null) {
		classElements08[0].id = divId2;
		classElements08[0].style.display = '';
	}
	//
	div = classElements08[0];
	collapseRest(div, num);

	if (saveInputId != null) {
		setSaveInputValue(saveInputId, divId);
	}
}

function subpestana(divId, num, name) {
	subpestana(divId, num, name, null);
}

function subpestana(divId, num, name, saveInputId) {
	var divId2 = name + divId;

	//
	var node = null;
	var tag = null;
	var classElements = new Array();
	if (node == null)
		node = document;
	if (tag == null)
		tag = '*';
	var els = node.getElementsByTagName(tag);
	var elsLen = els.length;
	var pattern = new RegExp('(^|\\\\s)' + divId2 + '(\\\\s|$)');
	for (i = 0, j = 0; i < elsLen; i++) {
		if (pattern.test(els[i].className)) {
			classElements[j] = els[i];
			j++;
		}
	}
	if (classElements[0] != null) {
		classElements[0].id = divId2;
		classElements[0].style.display = '';
	}
	//
	divId2 = classElements[0];
	subcollapseRest(divId2, num, name);

	if (saveInputId != null) {
		setSaveInputValue(saveInputId, divId);
	}
}

function setSaveInputValue(id, value) {

	var saveInput = document.getElementById(id);

	if (saveInput != null) {
		saveInput.value = value;
	}
}
function collapseRest(displayedDiv, num) {
	var node = null;
	var tag = null;
	var pestanas = num;
	for ( var j = 1; j <= pestanas; j++) {
		// alert("j="+j+" / pestaÃ±as="+pestanas);
		var divId = "pestana" + j;
		var node = null;
		var tag = null;
		var classElements09 = new Array();
		if (node == null)
			node = document;
		if (tag == null)
			tag = '*';
		var els = node.getElementsByTagName(tag);
		var elsLen = els.length;
		var e = 0;
		var pattern = new RegExp('(^|\\\\s)' + divId + '(\\\\s|$)');
		for (i = 0, e = 0; i < elsLen; i++) {
			if (pattern.test(els[i].className)) {
				classElements09[e] = els[i];
				e++;
			}
		}
		if (classElements09[0] != null) {
			classElements09[0].id = divId;
			// alert(classElements09[0].id);
			if (classElements09[0].id != displayedDiv.id)
				classElements09[0].style.display = 'none';
			// alert(classElements02[0].id);
		}
	}

}
function subcollapseRest(displayedDiv, num, name) {
	var node = null;
	var tag = null;
	var pestanas = num;
	// alert(displayedDiv.id);

	for ( var j = 1; j <= pestanas; j++) {
		// alert("j="+j+" / pestaÃ±as="+pestanas);
		var divId = name + j;
		var node = null;
		var tag = null;
		var classElements02 = new Array();
		if (node == null)
			node = document;
		if (tag == null)
			tag = '*';
		var els = node.getElementsByTagName(tag);
		var elsLen = els.length;
		var e = 0;
		var pattern = new RegExp('(^|\\\\s)' + divId + '(\\\\s|$)');
		for (i = 0, e = 0; i < elsLen; i++) {
			if (pattern.test(els[i].className)) {
				classElements02[e] = els[i];
				e++;
			}
		}
		if (classElements02[0] != null) {
			classElements02[0].id = divId;
			if (classElements02[0].id != displayedDiv.id)
				classElements02[0].style.display = 'none';
			// alert(classElements02[0].id);
		}
	}
}
function mostrarVentanaErrores() {
	var id = "";
	var divEncontrado = 0;
	var allHTMLTags = document.getElementsByTagName("*");
	for (i = 0; i < allHTMLTags.length; i++) {
		if (allHTMLTags[i].id == "divErrores") {
			divEncontrado = 1;
		}
		if (allHTMLTags[i].className == "dojoDialogError" && divEncontrado == 1) {
			id = allHTMLTags[i].id;
		}
	}

	if (id != "") {
		wmErrorVar = dojo.widget.createWidget("dialog", {
			id :"wmId",
			bgOpacity :0.5,
			toggle :"fade",
			bgColor :"white",
			toggleDuration :0
		}, dojo.byId(id));
		var btn0 = document.getElementById("btnCerrarVentanaError");
		wmErrorVar.setCloseControl(btn0);
		wmErrorVar.show();
	}
}
function cargarPagina(p, plantilla) {
	id = "";
	url = "";

	// 1. obtenemos el id de la plantilla ajax idLinkA4j
	var allHTMLTags = document.getElementsByTagName("a");
	for (i = 0; i != allHTMLTags.length; i++) {
		if (allHTMLTags[i].className == plantilla) {
			id = allHTMLTags[i].id;
		}
	}

	if (id != "") {
		// 2. A traves de la plantilla obtenemos la url que se quiere paginar
		cod = id.replace(":" + plantilla, "");
		var item = document.getElementById(id);
		var clickFunction = item.onclick + "";
		ini = clickFunction.lastIndexOf("actionUrl") + 12;
		var url = "";
		var concatenar = 1;
		for (i = ini; i != clickFunction.length; i++) {
			if (clickFunction.charAt(i) == "'"
					|| clickFunction.charAt(i) == "\"") {
				concatenar = 0;
			}
			if (concatenar == 1) {
				url = url + clickFunction.charAt(i);
			}
		}
		// 3. cargamos la pÃ¡gina
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','" + cod
				+ "','onclick',{'parameters':{'" + id + "':'" + id
				+ "','currentPage':'" + p + "'},'actionUrl':'" + url + "'});";
		eval(clickFunction);
		return false;
	}
}
function cargarPaginaSubsistema(p, plantilla, subsistema) {
	var id = "";
	// 1. obtenemos el id de la plantilla ajax idLinkA4j
	var allHTMLTags = document.getElementsByTagName("a");
	for (i = 0; i != allHTMLTags.length; i++) {
		if (allHTMLTags[i].className == plantilla) {
			id = allHTMLTags[i].id;
		}
	}
	if (id != "") {
		// 2. A traves de la plantilla obtenemos la url que se quiere paginar
		var cod = id.replace(":" + plantilla, "");

		var item = document.getElementById(id);
		var clickFunction = item.onclick + "";
		ini = clickFunction.lastIndexOf("actionUrl") + 12;
		var url = "";
		var concatenar = 1;
		for (i = ini; i != clickFunction.length; i++) {
			if (clickFunction.charAt(i) == "'"
					|| clickFunction.charAt(i) == "\"") {
				concatenar = 0;
			}
			if (concatenar == 1) {
				url = url + clickFunction.charAt(i);
			}
		}
		// 3. cargamos la pagina
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','" + cod
				+ "','onclick',{'parameters':{'" + id + "':'" + id
				+ "','currentPage':'" + p + "'},'actionUrl':'" + url + "'});";
		eval(clickFunction);
		return false;
	}
}
function cargarPaginaSubsistemaConPageSize(p, plantilla, subsistema, pageSize) {
	var id = "";
	// 1. obtenemos el id de la plantilla ajax idLinkA4j
	var allHTMLTags = document.getElementsByTagName("a");
	for (i = 0; i != allHTMLTags.length; i++) {
		if (allHTMLTags[i].className == plantilla) {
			id = allHTMLTags[i].id;
		}
	}
	if (id != "") {
		// 2. A traves de la plantilla obtenemos la url que se quiere paginar
		var cod = id.replace(":" + plantilla, "");

		var item = document.getElementById(id);
		var clickFunction = item.onclick + "";
		ini = clickFunction.lastIndexOf("actionUrl") + 12;
		var url = "";
		var concatenar = 1;
		for (i = ini; i != clickFunction.length; i++) {
			if (clickFunction.charAt(i) == "'"
					|| clickFunction.charAt(i) == "\"") {
				concatenar = 0;
			}
			if (concatenar == 1) {
				url = url + clickFunction.charAt(i);
			}
		}
		// 3. cargamos la pagina
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','" + cod
				+ "','onclick',{'parameters':{'" + id + "':'" + id
				+ "','currentPage':'" + p + "','pageSize':'" + pageSize
				+ "'},'actionUrl':'" + url + "'});";
		eval(clickFunction);
		return false;
	}
}
// funcion para limpiar los formularios de busqueda.
function limpia_formulario_busqueda(formulario) {
	var form = document.forms[formulario];
	for (i = 0; i < form.length; i++) {
		var temp = form.elements[i];
		if (temp.id != null && temp.id != ""
				&& temp.id != "javax.faces.ViewState") {
			if ((temp.type == "text")) {
				temp.value = '';
			}
			if ((temp.type == "checkbox")) {
				temp.checked = false;
			}
			if ((temp.type == "select-one")) {
				temp.selectedIndex = 0;
			}
			if ((temp.type == "hidden")) {
				temp.value = '';
			}
		}
	}
}
function mostrarVentanaEmergente(multi) {
	var id = "";
	var divEncontrado = 0;
	var divErroresVentana = "divErroresVentana" + multi;
	var dojoDialogVentana = "dojoDialogVentana" + multi;
	var btnCerrarVentana = "btnCerrarVentana" + multi;
	var allHTMLTags = document.getElementsByTagName("*");
	for (i = 0; i < allHTMLTags.length; i++) {
		if (allHTMLTags[i].id == divErroresVentana) {
			divEncontrado = 1;
		}
		if (allHTMLTags[i].className == dojoDialogVentana && divEncontrado == 1) {
			id = allHTMLTags[i].id;
		}
	}
	if (id != "") {
		try {
			ventana = dojo.widget.createWidget("dialog", {
				id :"wmId",
				bgOpacity :0.5,
				toggle :"fade",
				bgColor :"white",
				toggleDuration :0
			}, dojo.byId(id));
			var btn0 = document.getElementById(btnCerrarVentana);
			ventana.setCloseControl(btn0);
			ventana.show();
		} catch (e) {
		}
	}
}

var nextStepClicked = false;
function disableSelectOneRow(datatableId, radioId) {
	if (!nextStepClicked) {
		var index = 0;
		var radioElement = document.getElementById(datatableId + index
				+ radioId);
		while (radioElement != null) {
			if (radioElement.checked) {
				radioElement.checked = false;
			}
			index++;
			radioElement = document.getElementById(datatableId + index
					+ radioId);
		}
	}
	return true;
}
function nextStep() {
	nextStepClicked = true;
}
// función para pasar fechas de criterios avanzados de la búsqueda de lectores
// por las persianas
function pasarFechas() {
	if (document
			.getElementsByName('listadoLectores:pgcCriteriosAvanzadosCollapsedState')[0].value == 'true') {
		document.getElementById('listadoLectores:txcFechaAltaHasta').disabled = false;
		document.getElementById('listadoLectores:txcFechaAltaDesde').disabled = false;
		document.getElementById('listadoLectores:txcFechaNacHasta').disabled = false;
		document.getElementById('listadoLectores:txcFechaNacDesde').disabled = false;
	}
}
function pasarFechasEjemplares() {
	if (document
			.getElementsByName('listEntityForm:pgcCriteriosAvanzadosCollapsedState')[0].value == 'true') {
		document.getElementById('listEntityForm:txcFechaRegistroDesde').disabled = false;
		document.getElementById('listEntityForm:txcFechaRegistroHasta').disabled = false;
		document.getElementById('listEntityForm:txcFechaInventarioDesde').disabled = false;
		document.getElementById('listEntityForm:txcFechaInventarioHasta').disabled = false;
		document.getElementById('listEntityForm:txcFechaSituacionDesde').disabled = false;
		document.getElementById('listEntityForm:txcFechaSituacionHasta').disabled = false;
	}
}
function isdefined(variable) {
	return (typeof (window[variable]) == "undefined") ? false : true;
}
function tNC(spanId, navImageId) {
	if (arbolActivo == true) {
		var navSpan = document.getElementById(spanId);
		var displayStyle = navSpan.style.display;
		if (displayStyle == 'none') {
			displayStyle = 'block'
		} else {
			displayStyle = 'none';
		}
		navSpan.style.display = displayStyle;
		if (navImageId != '') {
			var navImage = document.getElementById(navImageId);
			if (navImage.src.indexOf("Ma") != -1) {
				navImage.src = navImage.src.replace('Ma', 'Me');
			} else {
				navImage.src = navImage.src.replace('Me', 'Ma');
			}
		}
	}
}
function quitarEstiloArbol(event) {
	var allHTMLTags = document.getElementsByTagName("a");
	for (i = 0; i != allHTMLTags.length; i++) {
		if (allHTMLTags[i].className.lastIndexOf("tB_") != -1) {
			allHTMLTags[i].style.backgroundColor = "#FFFFFF";
			allHTMLTags[i].style.color = "#000000";
			allHTMLTags[i].style.padding = "0";
			allHTMLTags[i].style.fontWeight = "normal";
		}
	}
}
function ponerEstilosArbol(event) {
	event.style.backgroundColor = "#009A54";
	event.style.color = "#FFFFFF";
	event.style.padding = "0 2px";
	event.style.fontWeight = "bold";
}
function cN(event, nodo, formulario, pagina) {
	quitarEstiloArbol(event);
	ponerEstilosArbol(event);
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','" + formulario
			+ "',event,{'parameters':{'nodoClicked':" + nodo + ",'"
			+ formulario + ":seleccionarNodo':'" + formulario
			+ ":seleccionarNodo'},'actionUrl':'" + pagina + "'})";
	try {
		eval(clickFunction);
	} catch (e) {
	}
	return;
}
var arbolActivo = true;
function cH(event, nodo, formulario) {
	if (arbolActivo == true) {
		quitarEstiloArbol(event);
		ponerEstilosArbol(event);
		var oculto = document.getElementById("seleccionarNodo");
		oculto.value = nodo;
	}
	return false;
}
function seleccionarRama(idArbol, idNodo){
if ((idArbol!='') && (idNodo!='')) {
quitarEstiloArbol(idArbol);
id="N:"+idNodo;
var elemento=$(id);
var elementoLI=elemento.parentNode;
var elementoSPAN=elementoLI.childNodes[1];
var elementoA=elementoSPAN.childNodes[0];
ponerEstilosArbol(elementoA);
abrirRamaArbol(elementoA);
}
}
function abrirRamaArbol(obj) {
	if (obj != undefined) {
		var padre = obj.parentNode;
		if (padre.className != 'dtree') {
			if ((padre.nodeName == 'ul') || (padre.nodeName == 'UL')) {
				padre.style.display = "block";
			}
		}
		padre = padre.parentNode;
		if (padre.className != 'dtree') {
			padre = padre.parentNode;
		}
		while (padre.className != 'dtree') {
			if ((padre.nodeName == 'ul') || (padre.nodeName == 'UL')) {
				padre.style.display = "block";
			} else if ((padre.nodeName == 'li') || (padre.nodeName == 'LI')) {
				padre.childNodes[0].src = padre.childNodes[0].src.replace('Ma',
						'Me');
			}
			padre = padre.parentNode;
		}
	}
}
if (isdefined('A4J')) {
	A4J.AJAX.onError = function(req, status, message) {
		alert("Ha caducado la sesión. Vuelva a entrar en la aplicación");
		location.href = '/librae-adminconfig/logOut.html';
	}
}
if (isdefined('A4J')) {
	A4J.AJAX.onExpired = function(loc, expiredMsg) {
		alert("Ha caducado la sesión. Vuelva a entrar en la aplicación");
		location.href = '/librae-adminconfig/logOut.html';
	}
}

Event.observe(document, 'click', loading_Ajax_Librae);
function loading_Ajax_Librae(event) {
	try {
		var element = Event.element(event);
		if ((document.getElementById('_viewRoot:status.start') != null)
				&& (event.button == '0')) {
			if ((element.nodeName == "SPAN") || (element.nodeName == "span")) {
				if ((element.parentNode.nodeName == "A")
						|| (element.parentNode.nodeName == "a")) {
					document.getElementById('_viewRoot:status.start').style.display = '';
				}
			}
			if ((element.nodeName == "img") || (element.nodeName == "IMG")) {
				if ((element.parentNode.nodeName == "SPAN")
						|| (element.parentNode.nodeName == "span")) {
					if ((element.parentNode.parentNode.nodeName == "A")
							|| (element.parentNode.parentNode.nodeName == "a")) {
						document.getElementById('_viewRoot:status.start').style.display = '';
					}
				}
			}
		}
	} catch (e) {

	}
}
var cmTemaCabecera = {
	prefix :'TemaCabecera',
	// HTML code to the left of the folder item
	mainFolderLeft :'',
	// HTML code to the right of the folder item
	mainFolderRight :'',
	// HTML code to the left of the regular item
	mainItemLeft :'',
	// HTML code to the right of the regular item
	mainItemRight :'',
	// sub menu display attributes
	// HTML code to the left of the folder item
	folderLeft :'',
	// HTML code to the right of the folder item
	folderRight :'',
	// HTML code to the left of the regular item
	itemLeft :'',
	// HTML code to the right of the regular item
	itemRight :'',
	// cell spacing for main menu
	mainSpacing :0,
	// cell spacing for sub menus
	subSpacing :0,
	// auto dispear time for submenus in milli-seconds
	delay :1000,
	// move the sub-submenus up a bit due to a white top border
	offsetSubAdjust : [ 0, -1 ]
// rest use default settings
};
// horizontal split, used only in sub menus
var cmTemaCabeceraHSplit = [
		_cmNoClick,
		'<td colspan="3" class="TemaCabeceraMenuSplit"><div class="TemaCabeceraMenuSplit"></div></td>' ];
// horizontal split, used only in main menu
var cmTemaCabeceraMainHSplit = [
		_cmNoClick,
		'<td colspan="3" class="TemaCabeceraMenuSplit"><div class="TemaCabeceraMenuSplit"></div></td>' ];
// vertical split, used only in main menu
var cmTemaCabeceraMainVSplit = [ _cmNoClick,
		'<div class="TemaCabeceraMenuVSplit">|</div>' ];

var cmTemaListado = {
	prefix :'TemaListado',
	// HTML code to the left of the folder item
	mainFolderLeft :'',
	// HTML code to the right of the folder item
	mainFolderRight :'',
	// HTML code to the left of the regular item
	mainItemLeft :'',
	// HTML code to the right of the regular item
	mainItemRight :'',
	// sub menu display attributes
	// HTML code to the left of the folder item
	folderLeft :'',
	// HTML code to the right of the folder item
	folderRight :'',
	// HTML code to the left of the regular item
	itemLeft :'',
	// HTML code to the right of the regular item
	itemRight :'',
	// cell spacing for main menu
	mainSpacing :0,
	// cell spacing for sub menus
	subSpacing :0,
	// auto dispear time for submenus in milli-seconds
	delay :1000,
	// move the sub-submenus up a bit due to a white top border
	offsetSubAdjust : [ 0, -1 ]
// rest use default settings
};
// horizontal split, used only in sub menus
var cmTemaListadoHSplit = [
		_cmNoClick,
		'<td colspan="3" class="TemaListadoMenuSplit"><div class="TemaListadoMenuSplit"></div></td>' ];
// horizontal split, used only in main menu
var cmTemaListadoMainHSplit = [
		_cmNoClick,
		'<td colspan="3" class="TemaListadoMenuSplit"><div class="TemaListadoMenuSplit"></div></td>' ];
// vertical split, used only in main menu
var cmTemaListadoMainVSplit = [ _cmNoClick,
		'<div class="TemaListadoMenuVSplit">|</div>' ];

/**
 * ============================================================================================================================================
 * FUNCIONES PARA EL REENDERIZADO DE LOS POPUPS DE LA APLICACIÓN
 * ==========================================================================================================================================
 */

/**
 * ######################################### LOCALIZADORES DE REGISTROS
 * BIBLIOGRÁFICOS ########################################
 */
/*
 * Boton aceptar
 */
function cargarEditPlantilla(event) {

	// Invocado desde el buscar RR.BB.
	if (document.getElementById("listadoRegistroBibliograficoForm") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','listadoRegistroBibliograficoForm',event,{'parameters':{'listadoRegistroBibliograficoForm:seleccionarPlantillaInicial':'listadoRegistroBibliograficoForm:seleccionarPlantillaInicial'},'actionUrl':'/librae-catalogacion/pages/registroBibliografico/listadoRegistroBibliografico.html'});";
	}

	// Si es llamado desde el localizador existente en el detalle de RR.BB.
	if (document.getElementById("verRegistroBibliograficoForm") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','verRegistroBibliograficoForm',event,{'parameters':{'verRegistroBibliograficoForm:seleccionPlantillaInicial':'verRegistroBibliograficoForm:seleccionPlantillaInicial'},'actionUrl':'/librae-catalogacion/pages/registroBibliografico/verRegistroBibliografico.html'});";
	}
	// Si es llamado desde el localizador existente en el editor de
	// catalogación.
	if (document.getElementById("editorCatalogacion") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacion',event,{'parameters':{'editorCatalogacion:seleccionarPlantilla':'editorCatalogacion:seleccionarPlantilla'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacion.html'});";
	}

	// si es llamado desde la página de edición de desiderata
	if (document.getElementById("desiderataEditarForm") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','desiderataEditarForm',event,{'parameters':{'desiderataEditarForm:seleccionarPlantillaListado':'desiderataEditarForm:seleccionarPlantillaListado'},'actionUrl':'/librae-adquisicion/pages/desideratas/editarDesiderata.html'});";
	}

	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}
/*
 * Se encarga de llamar a la función del MB encargada de limpiar de session una
 * posible seleccion de plantilla previa
 */
function borrarPlantillaSessionRegistro(event) {
	// Localizador Invocado desde el buscar RR.BB.
	if (document.getElementById("listadoRegistroBibliograficoForm") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','listadoRegistroBibliograficoForm',event,{'parameters':{'listadoRegistroBibliograficoForm:cancelarPlantilla':'listadoRegistroBibliograficoForm:cancelarPlantilla'},'actionUrl':'/librae-catalogacion/pages/registroBibliografico/listadoRegistroBibliografico.html'});";
	}

	// Localizador invocado desde el localizador existente en el detalle de
	// RR.BB.
	if (document.getElementById("verRegistroBibliograficoForm") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','verRegistroBibliograficoForm',event,{'parameters':{'verRegistroBibliograficoForm:cancelarPlantilla':'verRegistroBibliograficoForm:cancelarPlantilla'},'actionUrl':'/librae-catalogacion/pages/registroBibliografico/verRegistroBibliografico.html'});";
	}
	// Localizador invocado desde el localizador existente en el editor de
	// catalogación.
	if (document.getElementById("editorCatalogacion") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacion',event,{'parameters':{'editorCatalogacion:cancelarPlantilla':'listEntityForm:cancelarPlantilla'},'actionUrl':'/librae-catalogacion/pages/registroBibliografico/editorCatalogacion.html'});";
	}
	// Localizador invocado desde el localizador existente en el editor de
	// desideratas.
	if (document.getElementById("desiderataEditarForm") != null) {

		var clickFunction = "A4J.AJAX.Submit('_viewRoot','desiderataEditarForm',event,{'parameters':{'desiderataEditarForm:btnCancelar':'desiderataEditarForm:btnCancelar'},'actionUrl':'/librae-adquisicion/pages/desideratas/editarDesiderata.html'});";
	}

	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Llamada desde el localizador de etiquetas de datos (Editor de catalogacion)
 */
function cargarEtiqueta(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacionForm',event,{'parameters':{'editorCatalogacionForm:addEtiquetas':'editorCatalogacionForm:addEtiquetas'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacion.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Llamada desde el localizador de plantillas, cuando se invoca desde la
 * pantalla listadoRegistros Boton Cancelar, se encarga de llamar a la función
 * del MB encargada de limpiar de session una posible seleccion de plantilla
 * previa
 */
function borrarEtiquetaSession(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacionForm',event,{'parameters':{'editorCatalogacionForm:borrarEtiquetasSession':'editorCatalogacionForm:borrarEtiquetasSession'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacion.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Llamada desde el localizador de etiquetas de CONTROL (Editor de catalogacion)
 */
function cargarEtiquetaControl(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacionForm',event,{'parameters':{'editorCatalogacionForm:addEtiquetasControl':'editorCatalogacionForm:addEtiquetasControl'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacion.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Llamada desde el localizador de etiquetas de CONTROL (Editor de catalogacion
 * de autoridades)
 */
function cargarEtiquetaControlAutoridad(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacionAutoridad',event,{'parameters':{'editorCatalogacionAutoridad:addEtiquetasControl':'editorCatalogacionAutoridad:addEtiquetasControl'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacionAutoridad.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}
/*
 * Llamada desde el localizador de subcampos de etiquetas (Editor de
 * catalogacion)
 */
function cargarSubcampo(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacionForm',event,{'parameters':{'editorCatalogacionForm:addSubcampos':'editorCatalogacionForm:addSubcampos'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacion.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

function borrarSubcamposSession(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacionForm',event,{'parameters':{'editorCatalogacionForm:borrarSubcamposSession':'editorCatalogacionForm:borrarSubcamposSession'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacion.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Llamada desde el localizador de subcampos, cuando se invoca desde la pantalla
 * listadoRegistros Boton Cancelar, se encarga de llamar a la función del MB
 * encargada de limpiar de session una posible seleccion de subcampos por un
 * evento anterior
 */

function vincularActivo(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','verRegistroBibliograficoForm',event,{'parameters':{'verRegistroBibliograficoForm:lnkVincularActivoHide':'verRegistroBibliograficoForm:lnkVincularActivoHide'},'actionUrl':'/librae-catalogacion/pages/registroBibliografico/verRegistroBibliografico.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

function limpiarLocalizadorBusquedaActivos(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','localizadorBusquedaActivos',event,{'parameters':{'localizadorBusquedaActivos:lnkBorrarLocalizadorActivos':'localizadorBusquedaActivos:lnkBorrarLocalizadorActivos'},'actionUrl':'/librae-catalogacion/pages/localizadores/localizadorBusquedaActivos.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/**
 * ######################################### LOCALIZADORES DE AUTORIDADES
 * ########################################
 */
/*
 * Llamada desde el localizador de plantillas, cuando se invoca desde la
 * pantalla de listado autoridades
 */
function cargarPlantillaAutoridad(event) {
	// Localizador Invocado desde el buscar Autoridad
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','listadoAutoridadForm',event,{'parameters':{'listadoAutoridadForm:seleccionarPlantillaAutoridadInicial':'listadoAutoridadForm:seleccionarPlantillaAutoridadInicial'},'actionUrl':'/librae-catalogacion/pages/autoridad/listadoAutoridad.html'})";
	// Localizador invocado desde el localizador existente en el detalle de
	// Autoridad
	if (document.getElementById("verAutoridadForm") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','verAutoridadForm',event,{'parameters':{'verAutoridadForm:seleccionPlantillaInicial':'verAutoridadForm:seleccionPlantillaInicial'},'actionUrl':'/librae-catalogacion/pages/autoridad/verAutoridad.html'});";
	}
	// Localizador invocado desde el localizador existente en el editor de
	// catalogación.
	if (document.getElementById("editorCatalogacionAutoridad") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacionAutoridad',event,{'parameters':{'editorCatalogacionAutoridad:seleccionarPlantilla':'editorCatalogacionAutoridad:seleccionarPlantilla'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacionAutoridad.html'});";
	}
	// Localizador invocado desde el asociarAutoridad a una etiqueta del RR.BB.
	if (document.getElementById("listAsociarAutoridadForm") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','listAsociarAutoridadForm',event,{'parameters':{'listAsociarAutoridadForm:seleccionarPlantillaAutoridadInicial':'listAsociarAutoridadForm:seleccionarPlantillaAutoridadInicial'},'actionUrl':'/librae-catalogacion/pages/autoridad/listadoAsociarAutoridad.html'});";
	}

	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Llamada desde el localizador de plantillas de autoridades, cuando se invoca
 * desde la pantalla listado Autoridades Boton Cancelar, se encarga de llamar a
 * la función del MB encargada de limpiar de session una posible seleccion de
 * plantilla previa
 */
function borrarPlantillaAutoridad(event) {
	// Localizador Invocado desde el buscar Autoridad
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','listadoAutoridadForm',event,{'parameters':{'listadoAutoridadForm:cancelarPlantillaAutoridad':'listadoAutoridadForm:cancelarPlantillaAutoridad'},'actionUrl':'/librae-catalogacion/pages/autoridad/listadoAutoridad.html'});";

	// Localizador invocado desde el localizador existente en el detalle de
	// Autoridad
	if (document.getElementById("verAutoridadForm") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','verAutoridadForm',event,{'parameters':{'verAutoridadForm:cancelarPlantilla':'verAutoridadForm:cancelarPlantilla'},'actionUrl':'/librae-catalogacion/pages/autoridad/verAutoridad.html'});";
	}
	// Localizador invocado desde el localizador existente en el editor de
	// catalogación.
	if (document.getElementById("editorCatalogacionAutoridad") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacionAutoridad',event,{'parameters':{'editorCatalogacionAutoridad:cancelarPlantilla':'editorCatalogacionAutoridad:cancelarPlantilla'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacionAutoridad.html'});";

	}
	// Localizador invocado desde el asociarAutoridad a una etiqueta del RR.BB.
	if (document.getElementById("listAsociarAutoridadForm") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','listAsociarAutoridadForm',event,{'parameters':{'listAsociarAutoridadForm:cancelarPlantillaAutoridad':'listAsociarAutoridadForm:cancelarPlantillaAutoridad'},'actionUrl':'/librae-catalogacion/pages/autoridad/listadoAsociarAutoridad.html'});";
	}

	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Llamada desde el localizador de etiquetas (Editor de autoridades)
 */
function cargarEtiquetaAutoridad(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacionAutoridad',event,{'parameters':{'editorCatalogacionAutoridad:addEtiquetas':'editorCatalogacionAutoridad:addEtiquetas'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacionAutoridad.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * >Borra la posible selección de etiquetas desde el localizador de etiquetas
 * (Editor de autoridades)
 */
function borrarEtiquetaAutoridad(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacionAutoridad',event,{'parameters':{'editorCatalogacionAutoridad:borrarEtiquetasSession':'editorCatalogacionAutoridad:borrarEtiquetasSession'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacionAutoridad.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Llamada desde el localizador de subcampos de etiquetas (Autoridad)
 */
function cargarSubcamposAutoridad(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacionAutoridad',event,{'parameters':{'editorCatalogacionAutoridad:addSubcampos':'editorCatalogacionAutoridad:addSubcampos'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacionAutoridad.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 */
function borrarSubcamposAutoridad(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','editorCatalogacionAutoridad',event,{'parameters':{'editorCatalogacionAutoridad:borrarSubcamposSession':'editorCatalogacionAutoridad:borrarSubcamposSession'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacionAutoridad.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

function limpiarDatosPopupEtSubcampo(event) {
	var clickFunction = "";
	// Proviene del popup de etiquetas de autoridad
	if (document.getElementById("listadoEtiquetasAutoridad") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','listadoEtiquetasAutoridad',event,{'parameters':{'listadoEtiquetasAutoridad:limpiarLocalizadorEtiqueta':'listadoEtiquetasAutoridad:limpiarLocalizadorEtiqueta'},'actionUrl':'/librae-catalogacion/pages/localizadores/localizadorEtiquetasAutoridad.html'});";
	}

	// Proviene del popup de subcampos desde Editor de Autoridades
	if (document.getElementById("listadoSubcamposAutoridad") != null) {
		var clickFunction = "A4J.AJAX.Submit('_viewRoot','listadoSubcamposAutoridad',event,{'parameters':{'listadoSubcamposAutoridad:borrarPopupSubcamposAutoridad':'listadoSubcamposAutoridad:borrarPopupSubcamposAutoridad'},'actionUrl':'/librae-catalogacion/pages/localizadores/localizadorSubcamposAutoridad.html'});";
	}

	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Llamada desde el localizadorPlantillaAsociarAutoridad (Autoridad)
 */
function cargarPlantillaAsociarAutoridad(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','listEntityForm',event,{'parameters':{'listEntityForm:seleccionarPlantillaAutoridadInicial':'listEntityForm:seleccionarPlantillaAutoridadInicial'},'actionUrl':'/librae-catalogacion/pages/autoridad/listadoAsociarAutoridad.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 */
function borrarPlantillaAsociarAutoridad(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','listEntityForm',event,{'parameters':{'listEntityForm:seleccionarPlantillaAutoridadInicial':'listEntityForm:seleccionarPlantillaAutoridadInicial'},'actionUrl':'/librae-catalogacion/pages/autoridad/listadoAsociarAutoridad.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/**
 * ######################################### LOCALIZADORES DE EJEMPLARES
 * ########################################
 */

/*
 */
function transferirRegistro(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','verRegistroBibliograficoForm',event,{'parameters':{'verRegistroBibliograficoForm:lnkTransferirRegistro2':'verRegistroBibliograficoForm:lnkTransferirRegistro2'},'actionUrl':'/librae-catalogacion/pages/registroBibliografico/verRegistroBibliografico.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 */
function asignarTituloSecundario(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','editEntityForm2',event,{'parameters':{'editEntityForm2:lnkTituloSecundarioHide':'editEntityForm2:lnkTituloSecundarioHide'},'actionUrl':'/librae-catalogacion/pages/ejemplar/verEjemplar.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/**
 * #################################################################
 * LOCALIZADOR DE AUTORIDADES - TRANSFERIR AUTORIDADES
 * ################################################################
 */

/*
 * Realiza la llamada al componente de verAutoridad cuyo action realiza la carga
 * de la autoridad seleccionada por el localizador
 */
function transferirAutoridad(event) {
	var clickFunction = "A4J.AJAX.Submit('_viewRoot','verAutoridadForm',event,{'parameters':{'verAutoridadForm:lnkTransferirAutoridadAceptar':'verAutoridadForm:lnkTransferirAutoridadAceptar'},'actionUrl':'/librae-catalogacion/pages/autoridad/verAutoridad.html'});";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/**
 * ######################################### LOCALIZADORES DE PUBLICACIONES
 * SERIADAS ########################################
 */
/*
 * Cuando se pulsa sobre CANCELAR del localizador de registros
 */
function limpiarLocalizador(event) {
	var clickFunction = null;

	// Localizador de registros (CRUD suscripciones)
	if (document.getElementById("crudSuscripcionForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','crudSuscripcionForm',event,{'parameters':{'crudSuscripcionForm:cancelarRegistro':'crudSuscripcionForm:cancelarRegistro'},'actionUrl':'/librae-pubseriadas/pages/suscripcion/form.html'});";
	}
	// Localizador de registros (listado suscripciones)
	if (document.getElementById("listEntityForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','listEntityForm',event,{'parameters':{'listEntityForm:cancelarRegistro':'listEntityForm:cancelarRegistro'},'actionUrl':'/librae-pubseriadas/pages/suscripcion/listadoSuscripcion.html'});";
	}

	// Localizador de modos de visualización
	if (document.getElementById("crudPeriodicidadForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','crudPeriodicidadForm',event,{'parameters':{'crudPeriodicidadForm:cancelarModoVisualizacion':'crudPeriodicidadForm:cancelarModoVisualizacion'},'actionUrl':'/librae-pubseriadas/pages/periodicidad/form.html'})";
	}

	// Localizador de suscripciones
	if (document.getElementById("crudPedidoSuscripcionForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','crudPedidoSuscripcionForm',event,{'parameters':{'crudPedidoSuscripcionForm:cancelarSuscripcion':'crudPedidoSuscripcionForm:cancelarSuscripcion'},'actionUrl':'/librae-pubseriadas/pages/pedidoSuscripciones/form.html'})";
	}
	if (document.getElementById("crudPedidoEncuadernacionForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','crudPedidoEncuadernacionForm',event,{'parameters':{'crudPedidoEncuadernacionForm:cancelarSuscripcion':'crudPedidoEncuadernacionForm:cancelarSuscripcion'},'actionUrl':'/librae-pubseriadas/pages/pedidoEncuadernacion/form.html'})";
	}

	// Localizador de desideratas
	if (document.getElementById("desiderataEditarForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','desiderataEditarForm',event,{'parameters':{'desiderataEditarForm:borrarRegistroSession':'desiderataEditarForm:borrarRegistroSession'},'actionUrl':'/librae-adquisicion/pages/desideratas/editarDesiderata.html'});";
	}

	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Carga la periodicidad
 */
function cargarPeriodicidad(event) {
	var clickFunction = null;
	if (document.getElementById("listEntityForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','listEntityForm',event,{'parameters':{'listEntityForm:seleccionarPeriodicidad':'listEntityForm:seleccionarPeriodicidad'},'actionUrl':'/librae-pubseriadas/pages/suscripcion/listadoSuscripcion.html'});";
	} else {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','crudSuscripcionForm',event,{'parameters':{'crudSuscripcionForm:seleccionarPeriodicidad':'crudSuscripcionForm:seleccionarPeriodicidad'},'actionUrl':'/librae-pubseriadas/pages/suscripcion/form.html'});";
	}
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Llamada desde el localizador de plantillas, cuando se invoca desde la
 * pantalla listadoRegistros Boton Cancelar, se encarga de llamar a la función
 * del MB encargada de limpiar de session una posible seleccion de plantilla
 * previa
 */
//function borrarPeriodicidadSession(event) {
//	var clickFunction = null;
//	if (document.getElementById("listEntityForm") != null) {
//		clickFunction = "A4J.AJAX.Submit('_viewRoot','listEntityForm',event,{'parameters':{'listEntityForm:cancelarPeriodicidad':'listEntityForm:cancelarPeriodicidad'},'actionUrl':'/librae-pubseriadas/suscripcion/onlistadoSuscripci.html'});";
//	} else {
//		// clickFunction =
//		// "A4J.AJAX.Submit('_viewRoot','crudSuscripcionForm',event,{'parameters':{'crudSuscripcionForm:cancelarPeriodicidad':'crudSuscripcionForm:cancelarPeriodicidad'},'actionUrl':'/librae-pubseriadas/suscripcion/form.html'});";
//		clickFunction = "A4J.AJAX.Submit('_viewRoot','crudSuscripcionForm',event,{'parameters':{'crudSuscripcionForm:cancelarPeriodicidad':'crudSuscripcionForm:cancelarPeriodicidad'},'actionUrl':'/librae-pubseriadas/pages/suscripcion/form.html'});";
//	}
//
//	try {
//		eval(clickFunction);
//	} catch (e) {
//		alert(e);
//	}
//	return;
//}

/*
 * Asigna un registro bibliografico.
 */
function reasignarRegistro(event) {
	var clickFunction = null;

	if (document.getElementById("crudSuscripcionForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','crudSuscripcionForm',event,{'parameters':{'crudSuscripcionForm:lnkReasignarRegistroHide':'crudSuscripcionForm:lnkReasignarRegistroHide'},'actionUrl':'/librae-pubseriadas/pages/suscripcion/form.html'});";
	}
	if (document.getElementById("listEntityForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','listEntityForm',event,{'parameters':{'listEntityForm:lnkReasignarRegistroHide':'listEntityForm:lnkReasignarRegistroHide'},'actionUrl':'/librae-pubseriadas/pages/suscripcion/listadoSuscripcion.html'});";
	}
	if (document.getElementById("desiderataEditarForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','desiderataEditarForm',event,{'parameters':{'desiderataEditarForm:reasignarRegistro':'desiderataEditarForm:reasignarRegistro'},'actionUrl':'/librae-adquisicion/pages/desideratas/editarDesiderata.html'});";
	}
	// librae-catalogacion-webapp/src/main/webapp/templates/defaultTemplate.xhtml
	if (document.getElementById("editEntityForm2") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','editEntityForm2',event,{'parameters':{'editEntityForm2:lnkReasignarRegistroHide':'editEntityForm2:lnkReasignarRegistroHide'},'actionUrl':'/librae-catalogacion/pages/ejemplar/verEjemplar.html'});";
	}
	if (document.getElementById("editorCatalogacionForm") != null) {
		// clickFunction =
		// "A4J.AJAX.Submit('_viewRoot','editorCatalogacionForm',event,{'parameters':{'editorCatalogacionForm:addRegistro':'editorCatalogacionForm:addRegistro'},'actionUrl':'/librae-catalogacion/pages/editorCatalogacion/editorCatalogacion.html'});";
		clickFunction = "oamSubmitForm('editorCatalogacionForm','editorCatalogacionForm:addRegistro');";
	}

	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Asigna el Modo de visualizacion a la periodicidad al pulsar aceptar
 */
function cargarModoVisualizacion(event) {

	var clickFunction = "A4J.AJAX.Submit('_viewRoot','crudPeriodicidadForm',event,{'parameters':{'crudPeriodicidadForm:seleccionarModoVisualizacion':'crudPeriodicidadForm:seleccionarModoVisualizacion'},'actionUrl':'/librae-pubseriadas/pages/periodicidad/form.html'});";

	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;

}

/*
 * Borra el Modo de visualizacion de session
 */
function borrarModoVisualizacionSession(event) {

	var clickFunction = "A4J.AJAX.Submit('_viewRoot','crudPeriodicidadForm',event,{'parameters':{'crudPeriodicidadForm:cancelarModoVisualizacion':'crudPeriodicidadForm:cancelarModoVisualizacion'},'actionUrl':'/librae-pubseriadas/pages/periodicidad/form.html'})";
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Asigna una suscripción a un pedido de suscripciones o un pedido de
 * encuadernaciones
 */
function cargarSuscripcion(event) {
	var clickFunction = null;

	if (document.getElementById("crudPedidoSuscripcionForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','crudPedidoSuscripcionForm',event,{'parameters':{'crudPedidoSuscripcionForm:seleccionarSuscripcion':'crudPedidoSuscripcionForm:seleccionarSuscripcion'},'actionUrl':'/librae-pubseriadas/pages/pedidoSuscripciones/form.html'});";
	}
	if (document.getElementById("crudPedidoEncuadernacionForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','crudPedidoEncuadernacionForm',event,{'parameters':{'crudPedidoEncuadernacionForm:seleccionarSuscripcion':'crudPedidoEncuadernacionForm:seleccionarSuscripcion'},'actionUrl':'/librae-pubseriadas/pages/pedidoEncuadernaciones/form.html'});";
	}

	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Borra la suscripción del session de un pedido de suscripciones o un pedido de
 * encuadernaciones
 */
function borrarSuscripcionSession(event) {
	var clickFunction = null;

	if (document.getElementById("crudPedidoSuscripcionForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','crudPedidoSuscripcionForm',event,{'parameters':{'crudPedidoSuscripcionForm:cancelarSuscripcion':'crudPedidoSuscripcionForm:cancelarSuscripcion'},'actionUrl':'/librae-pubseriadas/pages/pedidoSuscripciones/form.html'})";
	}
	if (document.getElementById("crudPedidoEncuadernacionForm") != null) {
		clickFunction = "A4J.AJAX.Submit('_viewRoot','crudPedidoEncuadernacionForm',event,{'parameters':{'crudPedidoEncuadernacionForm:cancelarSuscripcion':'crudPedidoEncuadernacionForm:cancelarSuscripcion'},'actionUrl':'/librae-pubseriadas/pages/pedidoEncuadernaciones/form.html'})";
	}

	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/*
 * Refresca visualización del cárdex al volver de un popup
 */
function refrescaCardex(event) {
	// var clickFunction =
	// "A4J.AJAX.Submit('_viewRoot','listEntityForm',event,{'parameters':{'listEntityForm:refrescaCardex':'listEntityForm:refrescaCardex'},'actionUrl':'/librae-pubseriadas/pages/suscripcion/listadoSuscripcion.html'});";
	var clickFunction = "oamSubmitForm('listEntityForm','listEntityForm:refrescaCardex',null,null);";

	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

/**
 * ################## PRODUCTOS IMPRESOS ##################
 */

/*
 * Acciona botón oculto de la página cuando se vuelve del popup de impresión y
 * se ha realizado al guna impresión previa.
 */
function retornoProductosImpresos(event) {
	var clickFunction = null;
	if (document.getElementById("editEntityForm2") != null) {
		var valor = document.getElementById("editEntityForm2:idTipoImprimir").value;
		if (valor == '0') {
			clickFunction = "oamSubmitForm('editEntityForm2','editEntityForm2:lnkActualizarImpresionesPendientes0');";
		} else if (valor == '1') {
			clickFunction = "oamSubmitForm('editEntityForm2','editEntityForm2:lnkActualizarImpresionesPendientes1');";
		} else if (valor == '2') {
			clickFunction = "oamSubmitForm('editEntityForm2','editEntityForm2:lnkActualizarImpresionesPendientes2');";
		}
	} else if (document.getElementById("listEntityForm") != null) {
		var valor = document.getElementById("listEntityForm:idTipoImprimir").value;
		if (valor == '0') {
			clickFunction = "oamSubmitForm('listEntityForm','listEntityForm:lnkActualizarImpresionesPendientes0');";
		} else if (valor == '1') {
			clickFunction = "oamSubmitForm('listEntityForm','listEntityForm:lnkActualizarImpresionesPendientes1');";
		} else if (valor == '2') {
			clickFunction = "oamSubmitForm('listEntityForm','listEntityForm:lnkActualizarImpresionesPendientes2');";
		}
	}
	try {
		eval(clickFunction);
	} catch (e) {
		alert(e);
	}
	return;
}

// Variables
var cmdBtnId = 'cmdbtn';
var formId = 'frm1';
// var checkBoxArrayId = 'chk';

// Funciones

function onChangeSelect(checkbox, checkBoxArrayId) {
	// Render the transfer button if one or more checkboxes are selected
	hideOrShowObject(formId, cmdBtnId, checkBoxArrayHasChecked(checkBoxArrayId));
}

/*
 * Browser-safe get object. Tries all three approaches to get an object by its
 * element Id.
 *
 * Param: objId - String - id of element to retrieve. Param: formId - String -
 * id of form (optional). Returns: element or null if not found.
 */
function getObj(objId, formId) {
	var fullId = objId;
	if (formId != null && formId.length > 0) {
		fullId = formId + ':' + objId;
	}
	var elem = null;
	if (document.getElementById) {
		elem = document.getElementById(fullId);
	} else if (document.all) {
		elem = document.all[fullId];
	} else if (document.layers) {
		elem = document.layers[fullId];
	}
	return elem;
}

/*
 * Browser-safe. Checks to see if an array of check boxes has any which are
 * checked. Boxes have ids like check[0], check[1], ... , check[n] where 'check'
 * is the base Id that has been assigned to the group.
 *
 * Param: arrayId - String - id of element group to change Returns: boolean
 * (true one or more checked) false (else)
 */
function checkBoxArrayHasChecked(arrayId) {
	for (i = 0;; i++) {
		id = arrayId + '[' + i + ']';
		elem = getObj(id);
		if (elem == null) {
			break;
		} else if (elem.checked) {
			return true;
		}
	}
	return false;
}

/*
 * Browser-safe. Sets the visibility of an element using CSS visibility
 *
 * Param: objId - String - id of element to change Param: state - boolean - true
 * (show element) false (hide element) Returns: nothing
 */
function hideOrShowObject(formId, objId, state) {
	var elem = getObj(objId, formId);
	if (elem != null) {
		if (state) {
			elem.style.visibility = 'visible';
		} else {
			elem.style.visibility = 'hidden';
		}
	}
}

/*
 * Browser-safe. Check or uncheck an array of checkboxes. Boxes have ids like
 * check[0], check[1], ... , check[n] where 'check' is the base Id that has been
 * assigned to the group.
 *
 * Param: arrayId - String - id of element group to change Param: state -
 * boolean - true (check all elements) false (uncheck all elements) Returns:
 * nothing
 */
function checkBoxArraySet(arrayId, state) {

	var listaChecks = document.getElementsByTagName("input");
	for ( var i = 0; i < listaChecks.length; i++) {
		if (listaChecks[i].type.toLowerCase() == "checkbox"
				&& listaChecks[i].id.lastIndexOf(arrayId + '[') != -1) {
			id = listaChecks[i].id;
			elem = getObj(id);
			if (elem == null) {
				break;
			} else {
				elem.checked = state;
			}
		}
	}

	/*
	 * for (i = 0; ; i++) { id = arrayId + '[' + i + ']'; elem = getObj(id); if
	 * (elem == null) { break; } else { elem.checked = state; } }
	 */
}

function setAll(state) {
	// Set the checkBox array on or off
	checkBoxArraySet(checkBoxArrayId, state);
	// Render the transfer button if one or more checkboxes are selected
	hideOrShowObject(formId, cmdBtnId, checkBoxArrayHasChecked(checkBoxArrayId));
}

function setAll(state, checkBoxArrayId) {
	// Set the checkBox array on or off
	checkBoxArraySet(checkBoxArrayId, state);
	// Render the transfer button if one or more checkboxes are selected
	hideOrShowObject(formId, cmdBtnId, checkBoxArrayHasChecked(checkBoxArrayId));
}

// Set the cmd button off until a checkbox has been changed
function setInitialState() {
	hideOrShowObject(formId, cmdBtnId, false);
}

function setDisplayState(boton) {
	boton = document.getElementById(boton);
	estilo = boton.style.display;
	boton.style.display = (estilo == "") ? "none" : ""
}

/*
 * para el caso de que una pÃƒÆ’Ã‚Â¡gina tenemos un ÃƒÆ’Ã‚Âºnico listado (1
 * subpestaÃƒÆ’Ã‚Â±a)
 */

var selectedAll = false;
function onChangeSelectAll(check, boton, idSelectAllPage, idListado) {
	state = check.checked;
	setAll(state, 'chk' + idListado);

	setDisplayState(boton + idListado + "1");
	setDisplayState(boton + idListado + "2");

	// se selecciona tb el check de la pagina actual
	checkAllPage = document.getElementById(idSelectAllPage);
	if (checkAllPage != null) {
		checkAllPage.checked = state;
	}
}

function onChangeSelectAllPage(check) {
	state = check.checked;
	setAll(state, 'chk');
}

/*
 * para el caso de que una misma pÃƒÆ’Ã‚Â¡gina tengamos varios listados
 * (subpestaÃƒÆ’Ã‚Â±as)
 */

function onChangeSelectAllSeveralPages(boton, checkBoxArrayId, state) {
	state = !state;

	setAllSeveralPages(state, checkBoxArrayId);

	setDisplayState(boton + "1");
	setDisplayState(boton + "2");

	return state;
}

function onChangeSelectAllPageSeveralPages(checkBoxArrayId, state) {
	state = !state;

	setAllSeveralPages(state, checkBoxArrayId);

	return state;
}

function setAllSeveralPages(state, checkBoxArrayId) {
	// Set the checkBox array on or off
	checkBoxArraySet(checkBoxArrayId, state);
	// Render the transfer button if one or more checkboxes are selected
	hideOrShowObject(formId, cmdBtnId, checkBoxArrayHasChecked(checkBoxArrayId));
}

function clearSelectAll(boton, idSelectAll) {
	check = document.getElementById(idSelectAll);
	if (check != null && check.checked) {
		check.checked = false;
		setDisplayState(boton + "1");
		setDisplayState(boton + "2");
	}
}

function clearSelectAllPage(idSelectAllPage) {
	check = document.getElementById(idSelectAllPage);
	if (check != null && check.checked) {
		check.checked = false;
	}
}

// =========== funcion para implementar la seleccion de un radioButton
/**
 * Envía la petición Ajax con el objeto seleccionado. Siempre se enviará un
 * único id del objeto seleccionado por la estructura del radioButton
 */
function actualizarSeleccionRadioButton(object, idRadioButton, idListado) {
	url = "/librae-adminconfig/blank.html?idListadoRadioButton=" + idListado;
	id = "idChk=" + idRadioButton;
	// Lanzamos la petición Ajax
	url = url + "&check=true&" + id;

	// alert("url definitiva a lanzar en POST = "+url);
	lanzarPeticionAjax(url, "POST");
	// alert("Seguinos para actualizar los campos del radioButton")
	actualizarEstilosFilaRadioButton(idListado);
	// limpiarSeleccionarPage(idPage);
}
// ===================== Función para subir un parámetro a session para el
// localizador
function subirParamSession(commandLink, idEtiqueta) {
	url = "/librae-adminconfig/blank.html?idParametroLocalizador=" + idEtiqueta;
	idValor = commandLink.className;
	url = url + "&idValorChk=" + idValor;
	lanzarPeticionAjax(url, "POST");
}

function subirParamSessionPorType(commandLink, idEtiqueta) {
	url = "/librae-adminconfig/pages/blank.html?idParametroLocalizadorType="
			+ idEtiqueta;
	idValor = commandLink.type;
	// alert("Valor a subir a session por type es: "+idValor);
	// alert("VALOR: "+idValor);
	url = url + "&idValorChk=" + idValor;
	// alert("Voy a lanzar petición ajax");
	lanzarPeticionAjax(url, "POST");
}

function subirParamSessionPorStyleclass(commandLink, nombresSubcamposEtiqueta) {
	url = "/librae-adminconfig/pages/blank.html?idParametroLocalizadorStyleClass="
			+ nombresSubcamposEtiqueta;
	idValor = commandLink.className;
	// alert("VALOR StyleClass: "+idValor);
	url = url + "&idValorChk2=" + idValor;
	// alert("Voy a lanzar petición ajax");
	lanzarPeticionAjax(url, "POST");
}

function subirParamPorHidden(idEtiqueta, idHidden) {
	url = "/librae-adminconfig/pages/blank.html?idParametroLocalizadorHidden="
			+ idEtiqueta;
	elem = document.getElementById(idHidden);
	// alert("ELEM?? :"+elem);
	if (elem != null) {
		idValor = elem.value;
		url = url + "&idValorChk=" + idValor;
		// alert("URL definitiva: "+url);
		// alert("Voy a lanzar petición ajax");
		lanzarPeticionAjax(url, "POST");

	}
}
/**
 * Subir dos parámetros en expresiones regulares distintas en un único parámetro
 * de sesión, dividiendo ambos parámetros por una cadena especial
 *
 * @param commandLink
 * @param idEtiqueta
 * @param nombresSubcamposEtiqueta
 * @return
 */
function subirParametrosSession(commandLink, nombresSubcamposEtiqueta) {
	url = "/librae-adminconfig/pages/blank.html?idParametroLocalizadorType="
			+ nombresSubcamposEtiqueta;
	idValor = commandLink.className;
	// alert("Valor a subir a session por type es: " + idValor);
	url = url + "&idValorChk=" + idValor;
	idValor = commandLink.type;
	// alert("VALOR StyleClass: " + idValor);
	url = url + "CARACTER_ESPECIAL" + idValor;
	// alert("Voy a lanzar petición ajax: " + url);
	lanzarPeticionAjax(url, "POST");
}

/**
 * Invocada desde la pantalla del editor de catalogación, para el borrado de los
 * campos READONLY, que asocian autoridades
 *
 * @param object:
 *            objeto que realiza la petición
 * @param idTxtSubcampo:
 *            id del inputText a limpiar el contenido
 * @return
 */
function limpiarValoresAutoridadAsociada(objeto, idTxtSubcampo, idEtiqueta) {
	// Primero subo el parámetro a session
	url = "/librae-adminconfig/pages/blank.html?idParametro=" + idEtiqueta;
	idValor = objeto.className;
	if (idValor != null) {
		url = url + "&idValorChk=" + idValor;
		lanzarPeticionAjax(url, "POST");
	}
	// Despues limpio los valores asignados
	i = 0;
	parar = false;
	patronComun = objeto.id;
	var element = patronComun.split(':');
	definitivo = element[0] + ":" + element[1] + ":" + element[2] + ":"
			+ element[3] + ":" + element[4] + ":";
	while (!parar) {
		id = definitivo + "tblSubcampos:" + i + ":" + idTxtSubcampo;
		elem = document.getElementById(id);
		if (elem != null) {
			$(id).value = '';
		} else {
			parar = true;
		}
		i = i + 1;
	}
}
// =============funciones para la multiseleccion ajax====================

function actualizarSeleccion(checkBox, idListado, idAll, idPage, multi) {
	url = "/librae-adminconfig/blank.html?idListado=" + idListado;
	id = "idChk=" + checkBox.className;
	check = "false";
	if (checkBox.checked) {
		check = "true";
	}
	url = url + "&check=" + check;
	url = url + "&" + id;
	lanzarPeticionAjax(url, "POST");

	if (multi == undefined) {
		idListado = '';
	}
	limpiarSeleccionarPage(idPage);

	// si seleccionar todos estaba marcado desmarco el resto
	checkTodos = document.getElementById(idAll);
	if (checkTodos != null && checkTodos.checked) {
		var listaChecks = document.getElementsByTagName("input");
		for ( var i = 0; i < listaChecks.length; i++) {
			if (listaChecks[i].type.toLowerCase() == "checkbox"
					&& listaChecks[i].id.lastIndexOf('chk' + idListado) != -1
					&& listaChecks[i].className != checkBox.className) {
				idChk = listaChecks[i].id;
				elem = getObj(idChk);
				elem.checked = false;
			}
		}
	}

	limpiarSeleccionarTodos(idAll, idListado);
	actualizarEstilosFila(idListado);
}

function actualizarSeleccionPagina(checkBox, idListado, idAll, multi) {
	var listaChecks = document.getElementsByTagName("input");
	var listaId = "";
	for ( var i = 0; i < listaChecks.length; i++) {

		if (multi == undefined) {
			if (listaChecks[i].type.toLowerCase() == "checkbox"
					&& listaChecks[i].id.lastIndexOf('chk') != -1) {
				idChk = listaChecks[i].id;
				id = listaChecks[i].className;
				elem = getObj(idChk);
				elem.checked = checkBox.checked;
				listaId = listaId + id + "_";
			}
		} else {
			if (listaChecks[i].type.toLowerCase() == "checkbox"
					&& listaChecks[i].id.lastIndexOf('chk' + idListado) != -1) {
				idChk = listaChecks[i].id;
				id = listaChecks[i].className;
				elem = getObj(idChk);
				elem.checked = checkBox.checked;
				listaId = listaId + id + "_";
			}
		}
	}

	if (listaId != "") {
		url = "/librae-adminconfig/blank.html?idListado=" + idListado;
		id = "idChk=" + listaId;
		url = url + "&check=" + checkBox.checked;
		url = url + "&" + id;
		lanzarPeticionAjax(url, "POST");
	}

	if (multi == undefined) {
		idListado = '';
	}

	limpiarSeleccionarTodos(idAll, idListado);
	actualizarEstilosFila(idListado);
}

function actualizarSeleccionTodos(checkBox, idListado, idPage, multi) {
	url = "/librae-adminconfig/blank.html?idListado=" + idListado;
	url = url + "&checkTodos=" + checkBox.checked;
	lanzarPeticionAjax(url, "POST");

	if (multi == undefined) {
		idListado = '';
	}
	onChangeSelectAll(checkBox, checkBox.className, idPage, idListado)
	actualizarEstilosFila(idListado);
}

/*
 * Checkea las etiquetas seleccionadas en el localizador de etiquetas,
 * subiendolas a session para posteriormente ser seleccionadas.
 */
function actualizarSeleccionLocalizador(checkBox, idListado) {
	url = "/librae-adminconfig/pages/blank.html?idListado=" + idListado;
	id = "idChk=" + checkBox.className;
	check = "false";
	if (checkBox.checked) {
		check = "true";
	}
	url = url + "&check=" + check;
	url = url + "&" + id;
	lanzarPeticionAjax(url, "POST");

	actualizarEstilosFila(idListado);

}

function lanzarPeticionAjax(url, metodo) {
	var ajax = new Ajax.Request(url, {
		method :metodo
	});
}

function limpiarSeleccionarTodos(idAll, idListado) {
	check = document.getElementById(idAll);
	if (check != null && check.checked) {
		check.checked = false;
		setDisplayState(check.className + idListado + "1");
		setDisplayState(check.className + idListado + "2");
	}
}

function limpiarSeleccionarPage(idPage) {
	check = document.getElementById(idPage);
	if (check != null && check.checked) {
		check.checked = false;
	}
}

function obtenerActualUrl() {
	url = document.location.href;
	url = url.replace("#", "");

	return url;
}

function actualizarEstilosFila(idListado) {
	var listaChecks = document.getElementsByTagName("input");
	// alert("Entra en actualizarEstilosFila con idListado" + idListado);
	for ( var i = 0; i < listaChecks.length; i++) {
		if (listaChecks[i].type.toLowerCase() == "checkbox"
				&& listaChecks[i].id.lastIndexOf('chk' + idListado) != -1) {
			idChk = listaChecks[i].id;
			elem = getObj(idChk);
			fila = elem.parentNode.parentNode;
			if (fila != null) {
				if (elem.checked) {
					fila.className = "FilaSelec";
				} else {
					pos = idChk;
					pos = pos.replace('chk' + idListado + '[', "");
					pos = pos.replace("]", "");
					if (pos % 2 == 0) {
						fila.className = "impar";
					} else {
						fila.className = "par";
					}
				}
			}
		}
	}
}

/**
 * Actualiza los estilos de la fila para un dataTable con seleccion por
 * radioButton
 */
function actualizarEstilosFilaRadioButton(idListado) {
	var listaChecks = document.getElementsByTagName("input");
	for ( var i = 0; i < listaChecks.length; i++) {
		if (listaChecks[i].type.toLowerCase() == "radio") {
			idChk = listaChecks[i].id;
			elem = getObj(idChk);
			fila = elem.parentNode.parentNode;
			if (fila != null) {
				if (elem.checked) {
					fila.className = "FilaSelec";
				} else {
					pos = idChk;
					pos = pos.replace('chk' + idListado + '[', "");
					pos = pos.replace("]", "");
					if (pos % 2 == 0) {
						fila.className = "impar";
					} else {
						fila.className = "par";
					}
				}
			}
		}
	}
}

function actualizarSeleccionPlantilla(checkBox, idListado) {
	url = "/librae-adminconfig/blank.html?idListado=" + idListado;
	id = "idChk=" + checkBox.className;
	check = "false";
	if (checkBox.checked) {
		check = "true";
	}
	url = url + "&check=" + check;
	url = url + "&" + id;
	lanzarPeticionAjax(url, "POST");

	actualizarEstilosFilaPlantilla(idListado);
}

function actualizarEstilosFilaPlantilla(idListado) {
	var listaChecks = document.getElementsByTagName("input");
	// alert("Entra en actualizarEstilosFila con idListado" + idListado);
	for ( var i = 0; i < listaChecks.length; i++) {
		if (listaChecks[i].type.toLowerCase() == "checkbox"
				&& listaChecks[i].id.lastIndexOf('chk' + idListado) != -1) {
			idChk = listaChecks[i].id;
			elem = getObj(idChk);
			fila = elem.parentNode.parentNode;
			if (fila != null) {
				if (elem.checked) {
					fila.className = "FilaSelec";
				} else {
					pos = idChk;
					pos = pos.replace('chk' + idListado + '[', "");
					pos = pos.replace("]", "");
					if (pos % 2 == 0) {
						fila.className = "impar";
					} else {
						fila.className = "par";
					}
				}
			}
		}
	}
}

// Variables
var idFila = "fila";
var cssFilaSelec = "FilaSelec";
var cssFilaPar = "par";
var cssFilaImpar = "impar";

var ultimaFilaSelec = null;
var arrayFilasSelec = new Array();

/*
 * Selecciona una fila y cambia su css
 */
function seleccionaFila_drcha(numFila) {
	var estiloFilaActual = eval(idFila + numFila + ".className");
	desmarcarTodasFilas(); // Deseleccionar fila
	marcarFila(numFila);

}

/*
 * Selecciona la fila seleccionada y deselecciona el resto
 */
function seleccionaFila(numFila) {
	// Estilo actual de la fila
	desmarcarTodasFilas();
	// Cambiamos el estilo de la fila actual
	marcarFila(numFila);
}

/*
 * Selecciona la fila seleccionada y deselecciona el resto
 */
function seleccionaVariasfilas(numFila, nombre) {
	// Cambiamos el estilo de la fila actual
	if (eval(nombre).checked) {
		marcarFila(numFila);
	} else {
		desmarcarFila(numFila);
	}
}

/*
 * Deselecciona todas las filas seleccionadas
 */
function desmarcarTodasFilas() {
	for ( var i = 0; i < arrayFilasSelec.length; i++) {
		if (arrayFilasSelec[i] == "S") {
			desmarcarFila(i);
		}
	}
}

/*
 * Selecciona todas las filas no seleccionadas
 */
function marcarTodasFilas() {

	for ( var i = 0; i < arrayFilasSelec.length; i++) {
		if (arrayFilasSelec[i] != "S") {
			marcarFila(i);
		}
	}
}

/*
 * Selecciona una fila, le cambia los estilos
 */
function desmarcarFila(numFila) {
	var estiloFila = (numFila % 2 == 0) ? cssFilaPar : cssFilaImpar;
	eval(idFila + numFila + ".className='" + estiloFila + "'");
	// Marcamos el valor como deseleccionado en el array de filas seleccionadas
	arrayFilasSelec[parseInt(numFila)] = undefined;
}

/*
 * Selecciona una fila, le cambia los estilos
 */
function marcarFila(numFila) {
	eval(idFila + numFila + ".className='" + cssFilaSelec + "'"); // Seleccionamos
	// la fila
	// actual
	// Marcamos el valor como seleccionado en el array de filas seleccionadas
	arrayFilasSelec[parseInt(numFila)] = "S";
}

/*
 * -----------------------------FUNCIONES ESPECIALES PARA LOS
 * CHECK--------------------------------------------
 */

/*
 * Deselecciona todas las filas de todas las pestaas
 */
function desmarcarTodo() {
	elem = document.getElementsByTagName('input');
	for (i = 0; i < elem.length; i++) {
		if (elem[i].type == 'checkbox') {
			elem[i].checked = false;
		}
	}

}

/*
 * Selecciona todas las filas no seleccionadas
 */
function marcarTodasFilasCheck(total, nombreform, nombrefil, nombreChk) {
	var formulario = document.forms[nombreform];

	for ( var i = 1; i < total + 1; i++) {
		nombrecheck = nombreChk + i;
		nombrefila = nombrefil + i;
		if (eval("formulario." + nombrecheck + ".checked") == false) {
			var check = eval("formulario." + nombrecheck);
			check.checked = true;
			document.getElementById(nombrefila).className = cssFilaSelec;
		}
	}
}

/*
 * Deselecciona todas las filas seleccionadas
 */
function desmarcarTodasFilasCheck(total, nombreform, nombrefil, nombreChk) {
	var formulario = document.forms[nombreform];

	for ( var i = 1; i < total + 1; i++) {
		nombrecheck = nombreChk + i;
		nombrefila = nombrefil + i;

		if (eval("formulario." + nombrecheck + ".checked") == true) {
			var check = eval("formulario." + nombrecheck);
			check.checked = false;
			var estiloFila = (i % 2 == 0) ? cssFilaPar : cssFilaImpar;
			document.getElementById(nombrefila).className = estiloFila;
		}
	}
}

/*
 * Selecciona la fila seleccionada y deselecciona el resto
 */
function seleccionaVariasFilasCheck(numFila, nombreform, nombrecheck,
		nombrefila) {
	// Cambiamos el estilo de la fila actual
	var formulario = document.forms[nombreform];
	if (eval("formulario." + nombrecheck + ".checked")) {
		document.getElementById(nombrefila).className = cssFilaSelec;
	} else {
		var estiloFila = (numFila % 2 == 0) ? cssFilaPar : cssFilaImpar;
		document.getElementById(nombrefila).className = estiloFila;
	}
}
// Envia el formulario cuando se pulsa un intro
function loading_intro(event, formulario, boton) {
	var element = Event.element(event);
	var code = event.keyCode;
	if (code == Event.KEY_RETURN) {
		return oamSubmitForm(formulario, boton);
	}
}