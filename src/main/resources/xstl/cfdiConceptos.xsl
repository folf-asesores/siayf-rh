<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0" xmlns:cfdi="http://www.campeche.gob.mx/cfd/1">
    <xsl:output method="text" version="1.0" encoding="UTF-8" indent="no"/>
    <xsl:include href="utilerias.xslt"/>
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
    
    
    
</xsl:stylesheet>