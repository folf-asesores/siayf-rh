<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0" xmlns:cfdi="http://www.campeche.gob.mx/cfd/1">
    <xsl:output method="text" version="1.0" encoding="UTF-8" indent="no"/>
    
    <xsl:include href="utilerias.xslt"/>
    <!-- Aquí iniciamos el procesamiento de la cadena original con su | inicial y el terminador || -->
    <xsl:template match="/">|<xsl:apply-templates select="/cfdi:Comprobante"/>||</xsl:template>
    <!--  Aquí iniciamos el procesamiento de los datos incluidos en el comprobante -->
    <xsl:template match="cfdi:Comprobante">
        <!-- Iniciamos el tratamiento de los atributos de comprobante -->
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@version"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@fecha"/>
        </xsl:call-template>        
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@LugarExpedicion"/>
        </xsl:call-template>        
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@FolioFiscalOrig"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@SerieFolioFiscalOrig"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@FechaFolioFiscalOrig"/>
        </xsl:call-template>        
        <!--
			Llamadas para procesar al los sub nodos del comprobante
		-->
        <xsl:apply-templates select="./cfdi:Emisor"/>
        <xsl:apply-templates select="./cfdi:Receptor"/>
        <xsl:apply-templates select="./cfdi:Conceptos"/>        
        
    </xsl:template>
    <!-- Manejador de nodos tipo Emisor -->
    <xsl:template match="cfdi:Emisor">
        <!-- Iniciamos el tratamiento de los atributos del Emisor -->
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@rfc"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@nombre"/>
        </xsl:call-template>
        <!--
			Llamadas para procesar al los sub nodos del comprobante
		-->
        <xsl:apply-templates select="./cfdi:DomicilioFiscal"/>
        <xsl:if test="./cfdi:ExpedidoEn">
            <xsl:call-template name="Domicilio">
                <xsl:with-param name="Nodo" select="./cfdi:ExpedidoEn"/>
            </xsl:call-template>
        </xsl:if>        
    </xsl:template>
    <!-- Manejador de nodos tipo Receptor -->
    <xsl:template match="cfdi:Receptor">
        <!-- Iniciamos el tratamiento de los atributos del Receptor -->
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@rfc"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@nombre"/>
        </xsl:call-template>
        <!--
			Llamadas para procesar al los sub nodos del Receptor
		-->
        <xsl:if test="./cfdi:Domicilio">
            <xsl:call-template name="Domicilio">
                <xsl:with-param name="Nodo" select="./cfdi:Domicilio"/>
            </xsl:call-template>
        </xsl:if>
    </xsl:template>
    <!-- Manejador de nodos tipo Conceptos -->
    <xsl:template match="cfdi:Conceptos">
        <!-- Llamada para procesar los distintos nodos tipo Concepto -->
        
        <xsl:for-each select="./*">            
			<xsl:for-each select="./*"> 
				<xsl:call-template name="Opcional">			
					 <xsl:with-param name="valor" select="."/>
				</xsl:call-template>	
			</xsl:for-each>
        </xsl:for-each>
        
    </xsl:template>
    
    <!-- Manejador de nodos tipo Complemento -->
    <xsl:template match="cfdi:Complemento">
        <xsl:for-each select="./*">
            <xsl:apply-templates select="."/>
        </xsl:for-each>
    </xsl:template>
    
    <!-- Manejador de nodos tipo ComplementoConcepto -->
    <xsl:template match="cfdi:Concepto">
        <xsl:for-each select="./*">
           <xsl:apply-templates select="."/>
        </xsl:for-each>
    </xsl:template>
    <!-- Manejador de nodos tipo Domicilio fiscal -->
    <xsl:template match="cfdi:DomicilioFiscal">
        <!-- Iniciamos el tratamiento de los atributos del Domicilio Fiscal -->
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@calle"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@noExterior"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@noInterior"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@colonia"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@localidad"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="./@referencia"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@municipio"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@estado"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@pais"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="./@codigoPostal"/>
        </xsl:call-template>
    </xsl:template>
    <!-- Manejador de nodos tipo Domicilio -->
    <xsl:template name="Domicilio">
        <xsl:param name="Nodo"/>
        <!-- Iniciamos el tratamiento de los atributos del Domicilio  -->
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="$Nodo/@calle"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="$Nodo/@noExterior"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="$Nodo/@noInterior"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="$Nodo/@colonia"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="$Nodo/@localidad"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="$Nodo/@referencia"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="$Nodo/@municipio"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="$Nodo/@estado"/>
        </xsl:call-template>
        <xsl:call-template name="Requerido">
            <xsl:with-param name="valor" select="$Nodo/@pais"/>
        </xsl:call-template>
        <xsl:call-template name="Opcional">
            <xsl:with-param name="valor" select="$Nodo/@codigoPostal"/>
        </xsl:call-template>
    </xsl:template>
    
</xsl:stylesheet>