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
	var texto = 'font-family: Arial, Verdana, Helvetica, sans-serif; font-size: 12px; font-weight: bold; color: #ffffff;';

    var superiorIzquierda = 'style="width: 15px; height: 35px; cursor: default; background-image: url(' + src + 'superior-izquierda.png);"';
    var superior = 'id="draggable" style="width: ' + (ancho - 51) + 'px; height: 35px; cursor: default; background-image: url(' + src + 'superior.png); ' + texto + '"';
    var celdaImagen = 'style="width: 15px; height: 35px; background-image: url(' + src + 'superior.png); ' + texto + '"';
	var superiorDerecha = 'style="width: 20px; height: 35px; cursor: default; background-image: url(' + src + 'superior-derecha.png);"';
	var izquierda = 'style="width: 7px; height: ' + (alto - 75) + 'px; cursor: default; background-image: url(' + src + 'izquierda.png);"';
	var centro = 'style="width: ' + (ancho - 22) + 'px; height: ' + (alto - 75) + 'px; background-color: #ffffff;"';
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
	+ '<iframe name="' + nombre + '" style="width: ' + (ancho - 22) + 'px; height: ' + (alto - 75) + 'px; background-color: #FFFFFF;" src="' + pagina + '" frameborder="0">'
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