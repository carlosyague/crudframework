var VentanaModal = {
	inicializado	: false,
	creado			: false,
	ancho			: 0,
	alto			: 0,
	sombra			: false,
	csombra			: null,
	tsombra			: 0,
	claseSombra		: "",
	ventana			: null,
	idVentana		: "",
	claseVentana	: "",
	MSIE			: false,
	fondo			: null,
	iframe			: null,
	claseFondo		: "",
	scrollPadre		: false,
	seguimiento		: true,
	getInstancia: function() {
		this.inicializar();
		this.crear();
		return this;
	},
	setSize: function(ancho, alto) {
		this.alto = parseInt(alto);
		this.ancho = parseInt(ancho);
		this.ventana.style.width = this.ancho + "px";
		this.ventana.style.height = this.alto + "px";
		this.csombra.style.width = this.ancho + "px";
		this.csombra.style.height = this.alto + "px";
		this.redimensionar();

	},
	setClaseVentana: function(nombreClaseVentana) {
		this.claseVentana = nombreClaseVentana;
		this.ventana.className = this.claseVentana;
	},
	setSombra: function(sombra) {
		if (sombra == true) {
			this.sombra = true;
			this.csombra.style.display = "inline";
		}
		else {
			this.sombra = false;
			this.csombra.style.display = "none";
		}
	},
	setScrollPadre: function(spadre) {
		if (spadre == true) {
			this.scrollPadre = true;
		} else {
			this.scrollPadre = false;
		}
	},
	setSeguimientoScroll: function(sScroll) {
		if (sScroll == true) {
			this.seguimiento = true;
		}
		else {
			this.seguimiento = false;
		}
	},
	setSombraSize: function(tsombra) {
		this.tsombra = tsombra;
		this.redimensionar();
	},
	setClaseSombra: function(claseSombra) {
		this.claseSombra = claseSombra;
		this.csombra.className = this.claseSombra;
	},
	setIdVentana: function(id) {
		this.idVentana = id;
		this.ventana.id = this.idVentana;
	},
	setClaseFondo: function(claseFondo) {
		this.claseFondo = claseFondo;
		this.fondo.className = this.claseFondo;
		if (this.MSIE)
			this.iframe.className = this.claseFondo;
	},
	setContenido: function(html) {
		this.ventana.innerHTML = html;
	},
	mostrar: function() {
		if (!this.scrollPadre) {
			document.body.style.overflow = "hidden";
		}
		this.fondo.style.display = "inline";
		this.ventana.style.display = "inline";
		if (this.sombra)
			this.csombra.style.display = "inline";
	},
	cerrar: function() {
		if (!this.scrollPadre) {
			document.body.style.overflow = "auto";
		}
		this.ventana.style.display = "none";
		this.csombra.style.display = "none";
		this.fondo.style.display = "none";
	},
	medio: function(v1, v2) {
		if (isNaN(v1) && v1.indexOf && v1.indexOf("px") != -1)
			v1 = v1.replace("px", "");
		if (isNaN(v2) && v2.indexOf && v2.indexOf("px") != -1)
			v2 = v2.replace("px", "");
		var aux = parseInt(v1) / 2;
		aux = aux - (parseInt(v2) / 2);
		return parseInt(aux) * (+1);
	},
	inicializar: function() {
		if (this.inicializado)
			return;
		window.onresize = function() {
			VentanaModal.redimensionar();
		};
		if (this.seguimiento) {
			window.onscroll = function() {
				VentanaModal.redimensionar();
			};
		}
		this.ancho = 300;
		this.alto = 200;
		this.sombra = true;
		this.tsombra = 5;
		this.claseSombra = "ventana-modal-sombra";
		this.claseFondo = "ventana-modal-fondo";
		this.claseVentana = "ventana-modal-ventana";
		if (navigator.userAgent.indexOf('MSIE') >= 0)
			this.MSIE = true;
		this.inicializado = true;
		this.crear();
	},
	redimensionar: function() {
		var top = 0;
		var left = 0;
		var alto = 0;
		var ancho = 0;
		var array = this.getAnchoAlto();
		var anchoVentana = array[0];
		var anchoDocumento = array[2];
		var altoVentana = array[1];
		var altoDocumento = array[3];
		if (this.MSIE) ancho = anchoVentana + "px";
		else ancho = "100%";
		alto = altoVentana + "px";
		if (this.MSIE) {
			this.fondo.style.width = ancho;
			this.fondo.style.height = alto;
			this.iframe.style.width = ancho;
			this.iframe.style.height = alto;
		}
		else {
			this.fondo.style.width = ancho;
			this.fondo.style.height = alto;
		}
		if (this.MSIE) {
			top = this.medio(altoDocumento, this.alto);
			left = this.medio(anchoVentana, this.ancho);
		}
		else {
			top = this.medio(altoDocumento, this.alto);
			left = this.medio(anchoVentana, this.ancho);
		}
		top = top + this.getScrollAlto();
		this.ventana.style.top = top + "px";
		this.ventana.style.left = left + "px";
		this.csombra.style.top = (parseInt(top) + this.tsombra) + "px";
		this.csombra.style.left = (parseInt(left) + this.tsombra) + "px";
	},
	crear: function() {
		if (this.creado)
			return;
		this.fondo = document.createElement("DIV");
		this.fondo.style.position = "absolute";
		this.fondo.style.left = "0px";
		this.fondo.style.top = "0px";
		this.fondo.style.display = "none";
		this.fondo.className = this.claseFondo;
		this.fondo.style.zIndex = 90000;
		this.fondo.style.textAlign = "center";
		document.body.appendChild(this.fondo);
		if (this.MSIE) {
			this.iframe = document.createElement("IFRAME");
			this.fondo.appendChild(this.iframe);
			this.iframe.src = "about:blank";
			this.iframe.frameBorder = "0";
			this.iframe.className = this.claseFondo;
		}
		this.ventana = document.createElement("DIV");
		document.body.appendChild(this.ventana);
		this.ventana.style.display = "none";
		this.ventana.style.position = "absolute";
		this.ventana.style.zIndex = 100000;
		this.ventana.style.width = this.ancho + "px";
		this.ventana.style.height = this.alto + "px";
		this.ventana.className = this.claseVentana;
		this.csombra = document.createElement("DIV");
		document.body.appendChild(this.csombra);
		this.csombra.style.display = "none";
		this.csombra.style.position = "absolute";
		this.csombra.style.zIndex = 95000;
		this.csombra.style.width = this.ancho + "px";
		this.csombra.style.height = this.alto + "px";
		this.csombra.className = this.claseSombra;

		this.creado = true;
		this.redimensionar();
	},
	getScrollAlto: function() {
		var scrollAlto;

		if (self.pageYOffset) {
			scrollAlto = self.pageYOffset;
		} else if (document.documentElement && document.documentElement.scrollTop) {
			scrollAlto = document.documentElement.scrollTop;
		} else if (document.body) {
			scrollAlto = document.body.scrollTop;
		}

		return scrollAlto;
	},
	getAnchoAlto: function() {
		var xScroll, yScroll;
		if (window.innerHeight && window.scrollMaxY) {
			xScroll = document.body.scrollWidth;
			yScroll = window.innerHeight + window.scrollMaxY;
		} else if (document.body.scrollHeight > document.body.offsetHeight) {
			xScroll = document.body.scrollWidth;
			yScroll = document.body.scrollHeight;
		} else {
			xScroll = document.body.offsetWidth;
			yScroll = document.body.offsetHeight;
		}
		var ventanaAncho, ventanaAlto;
		if (self.innerHeight) {
			ventanaAncho = self.innerWidth;
			ventanaAlto = self.innerHeight;
		} else if (document.documentElement && document.documentElement.clientHeight) {
			ventanaAncho = document.documentElement.clientWidth;
			ventanaAlto = document.documentElement.clientHeight;
		} else if (document.body) {
			ventanaAncho = document.body.clientWidth;
			ventanaAlto = document.body.clientHeight;
		}
		if(yScroll < ventanaAlto) {
			paginaAlto = ventanaAlto;
		} else {
			paginaAlto = yScroll;
		}
		if(xScroll < ventanaAncho) {
			paginaAncho = ventanaAncho;
		} else {
			paginaAncho = xScroll;
		}
		arrayAnchoAltoPagina = new Array(paginaAncho, paginaAlto, ventanaAncho, ventanaAlto);
		return arrayAnchoAltoPagina;
	}
};
function abrirVentanaFija(pagina, ancho, alto, nombre, titulo, srcRelativo) {
	VentanaModal.inicializar();
    VentanaModal.setSize(ancho, alto);
    VentanaModal.setClaseVentana("");
	VentanaModal.setIdVentana("ventana-modal-ventana");
    var src = '../../images/popup/ventana-fija/';
    if ((srcRelativo!=null) && (srcRelativo!='undefined')){
    	src = srcRelativo + '/images/popup/ventana-fija/';
    }
	var iframe = '<iframe src="' + pagina + '" name="' + nombre + '" style="width: ' + (ancho - 22) + 'px; height: ' + (alto - 75) + 'px;"  frameborder="0"></iframe>';
	var over = 'onMouseOver="this.src=\'' + src + 'cerrarover.gif\'"';
	var out = 'onMouseOut="this.src=\'' + src + 'cerrar.gif\'"';
	var onClick = 'onclick="VentanaModal.cerrar()"';
	var atributos = 'alt="Cerrar" title="Cerrar ventana" style="cursor: pointer;"';
//	var cerrar = '<img src="' + src + 'cerrar.gif" ' + atributos + ' ' + onClick + ' ' + over + ' ' + out + ' />';
	var cerrar = '';
	var tabla = ' border="0" cellspacing="0" cellpadding="0"';
	var texto = 'font-family: Arial, Verdana, Helvetica, sans-serif; font-size: 12px; font-weight: bold; color: #E7E7E7;';
	var superiorIzquierda = 'style="width: 15px; height: 35px; cursor: default; background-image: url(' + src + 'superior-izquierda.png);"';
	var superior = 'id="draggable" style="width: ' + (ancho - 51) + 'px; height: 35px; cursor: default; background-image: url(' + src + 'superior.png); ' + texto + '"';
	var celdaImagen = 'style="width: 15px; height: 35px; background-image: url(' + src + 'superior.png); ' + texto + '"';
	var superiorDerecha = 'style="width: 20px; height: 35px; cursor: default; background-image: url(' + src + 'superior-derecha.png);"';
	var izquierda = 'style="width: 7px; height: ' + (alto - 75) + 'px; cursor: default; background-image: url(' + src + 'izquierda.png);"';
	var centro = 'style="width: ' + (ancho - 22) + 'px; height: ' + (alto - 75) + 'px; background-color: #E7E7E7;"';
	var derecha = 'style="width: 15px; height: ' + (alto - 75) + 'px; cursor: default; background-image: url(' + src + 'derecha.png);"';
	var inferiorIzquierda = 'style="width: 15px; height: 40px; cursor: default; background-image: url(' + src + 'inferior-izquierda.png);"';
	var inferior = 'style="width: ' + (ancho - 36) + 'px; height: 40px; cursor: default; background-image: url(' + src + 'inferior.png);"';
	var inferiorDerecha = 'style="width: 20px; height: 40px; cursor: default; background-image: url(' + src + 'inferior-derecha.png);"';
	if (VentanaModal.MSIE) {
		superiorIzquierda = 'style="width: 15px; height: 35px; cursor: default; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src=\'' + src + 'superior-izquierda.png\');"'
		superiorDerecha = 'style="width: 20px; height: 35px; cursor: default; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src=\'' + src + 'superior-derecha.png\');"';
		derecha = 'style="width: 15px; height: ' + (alto - 75) + 'px; cursor: default; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src=\'' + src + 'derecha.png\');"';
		inferiorIzquierda = 'style="width: 15px; height: 40px; cursor: default; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src=\'' + src + 'inferior-izquierda.png\');"';
		inferior = 'style="width: ' + (ancho - 36) + 'px; height: 40px; cursor: default; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src=\'' + src + 'inferior.png\');"';
		inferiorDerecha = 'style="width: 20px; height: 40px; cursor: default; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src=\'' + src + 'inferior-derecha.png\');"';
	}
	var html = ''
	+ '<table' + tabla + '>'
	+ '<tr><td><table' + tabla + '>'
	+ '<tr><td ' + superiorIzquierda + '>&nbsp;</td>'
	+ '<td ' + superior + '>' + titulo + '</td>'
	+ '<td ' + celdaImagen + '>' + cerrar + '</td>'
	+ '<td ' + superiorDerecha + '>&nbsp;</td>'
	+ '</tr></table></td></tr><tr><td>'
	+ '<table ' + tabla + '>'
	+ '<tr><td ' + izquierda + '>&nbsp;</td>'
	+ '<td ' + centro + '>' + iframe + '</td>'
	+ '<td ' + derecha + '>&nbsp;</td>'
	+ '</tr></table></td></tr><tr><td>'
	+ '<table ' + tabla + '>'
	+ '<tr><td ' + inferiorIzquierda +'>&nbsp;</td>'
	+ '<td ' + inferior + '>&nbsp;</td>'
	+ '<td ' + inferiorDerecha + '>&nbsp;</td>'
	+ '</tr></table></td></tr></table>';

    VentanaModal.setContenido(html);
    VentanaModal.mostrar();
}
function cerrar(){
//	alert("entra en el cerrar de RUIDO");
//	parent.location.href = parent.location;
	parent.VentanaModal.cerrar();

}
function abrirVentana(pagina, diferencia_ancho_pagina, diferencia_alto_pagina, nombre, titulo, srcRelativo) {
    VentanaModal.inicializar();
//	VentanaModal.setScrollPadre(false);
    if (titulo == null)
    	titulo = "";
    var array = VentanaModal.getAnchoAlto();
    ancho = parseInt(array[2]) - diferencia_ancho_pagina;
    alto = parseInt(array[3]) - diferencia_alto_pagina;

    var src = '../../images/popup/ventana-fija/';
    if ((srcRelativo!=null) && (srcRelativo!='undefined')){
    	src = srcRelativo + '/images/popup/ventana-fija/';
    }
    //  var img = 'src="../../images/popup/ventana/cerrar.gif" onclick="VentanaModal.cerrar()" style="cursor: pointer;" alt="cerrar" title="Cerrar ventana"';
    var img = '';
    var tabla = ' border="0" cellspacing="0" cellpadding="0" align="center"';
	var texto = 'font-family: Arial, Verdana, Helvetica, sans-serif; font-size: 12px; font-weight: bold; color: #E7E7E7;';
    var superiorIzquierda = 'style="width: 15px; height: 35px; cursor: default; background-image: url(' + src + 'superior-izquierda.png);"';
    var superior = 'id="draggable" style="width: ' + (ancho - 51) + 'px; height: 35px; cursor: default; background-image: url(' + src + 'superior.png); ' + texto + '"';
    var celdaImagen = 'style="width: 15px; height: 35px; background-image: url(' + src + 'superior.png); ' + texto + '"';
	var superiorDerecha = 'style="width: 20px; height: 35px; cursor: default; background-image: url(' + src + 'superior-derecha.png);"';
	var izquierda = 'style="width: 7px; height: ' + (alto - 75) + 'px; cursor: default; background-image: url(' + src + 'izquierda.png);"';
	var centro = 'style="width: ' + (ancho - 22) + 'px; height: ' + (alto - 75) + 'px; background-color: #E7E7E7;"';
	var derecha = 'style="width: 15px; height: ' + (alto - 75) + 'px; cursor: default; background-image: url(' + src + 'derecha.png);"';
	var inferiorIzquierda = 'style="width: 15px; height: 40px; cursor: default; background-image: url(' + src + 'inferior-izquierda.png);"';
	var inferior = 'style="width: ' + (ancho - 36) + 'px; height: 40px; cursor: default; background-image: url(' + src + 'inferior.png);"';
	var inferiorDerecha = 'style="width: 20px; height: 40px; cursor: default; background-image: url(' + src + 'inferior-derecha.png);"';
	if (VentanaModal.MSIE) {
		superiorIzquierda = 'style="width: 15px; height: 35px; cursor: default; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src=\'' + src + 'superior-izquierda.png\');"'
		superiorDerecha = 'style="width: 20px; height: 35px; cursor: default; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src=\'' + src + 'superior-derecha.png\');"';
		derecha = 'style="width: 15px; height: ' + (alto - 75) + 'px; cursor: default; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src=\'' + src + 'derecha.png\');"';
		inferiorIzquierda = 'style="width: 15px; height: 40px; cursor: default; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src=\'' + src + 'inferior-izquierda.png\');"';
		inferior = 'style="width: ' + (ancho - 36) + 'px; height: 40px; cursor: default; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src=\'' + src + 'inferior.png\');"';
		inferiorDerecha = 'style="width: 20px; height: 40px; cursor: default; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src=\'' + src + 'inferior-derecha.png\');"';
	}
    var html = ''
    + '<table style="width: ' + ancho + 'px; height: ' + alto + 'px;"' + tabla + '>'
	+ '<tr><td><table ' + tabla + '>'
	+ '<tr><td ' + superiorIzquierda + '>&nbsp;</td>'
	+ '<td ' + superior + '>' + titulo + '</td>'
	+ '<td ' + celdaImagen + '>' + img + '</td>'
	+ '<td ' + superiorDerecha + '>&nbsp;</td>'
	+ '</tr></table></td></tr><tr><td>'
	+ '<table style="width: ' + ancho + 'px; height: ' + (alto - 52) + 'px;"' + tabla + '>'
	+ '<tr><td ' + izquierda + '>&nbsp;</td>'
	+ '<td ' + centro + '>'
	+ '<iframe name="' + nombre + '" style="width: ' + (ancho - 22) + 'px; height: ' + (alto - 75) + 'px; background-color: #E7E7E7;" src="' + pagina + '" frameborder="0">'
	+ '</iframe></td><td ' + derecha + '>&nbsp;</td>'
	+ '</tr></table></td></tr><tr><td>'
	+ '<table width="100%"' + tabla + '><tr>'
	+ '<td ' + inferiorIzquierda + '>&nbsp;</td><td ' + inferior + '>&nbsp;</td>'
	+ '<td ' + inferiorDerecha + '>&nbsp;</td></tr></table></td></tr></table>';
    VentanaModal.setSize(ancho, alto);
    VentanaModal.setClaseVentana("");
    VentanaModal.setContenido(html);
    VentanaModal.mostrar();
}
function cerrar(){
	parent.VentanaModal.cerrar();
}