<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
                xmlns="http://myGame/tux" 
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                >  
    
    <xsl:output method="html"/>
    
    <xsl:param name="niveau1" select="1"/> 
    <xsl:param name="niveau2" select="2"/> 
    <xsl:param name="niveau3" select="3"/> 
    <xsl:param name="niveau4" select="4"/> 
    <xsl:param name="niveau5" select="5"/> 
     
    <xsl:template match="/">
        <html>
            <head>
                <title>Page dictionnaire</title>
            </head>
            <body>
                <h1>Affichage des mots par ordre alphabétique</h1> <br/>  
                
                <h2>Niveau 1<br/>
                <!-- appel le template qui va afficher les mots de niveau 1-->
                    <xsl:apply-templates select="//mot[@niveau = $niveau1]">
                        <xsl:sort select="." order="ascending"/>
                    </xsl:apply-templates>
               </h2> <br/>
                <h2>Niveau 2<br/>
                <!-- appel le template qui va afficher les mots de niveau 1-->
                    <xsl:apply-templates select="//mot[@niveau = $niveau2]">
                        <xsl:sort select="." order="ascending"/>
                    </xsl:apply-templates>
                </h2> 
                <br/>
                <h2>Niveau 3<br/>
                <!-- appel le template qui va afficher les mots de niveau 1-->
                    <xsl:apply-templates select="//mot[@niveau = $niveau3]">
                        <xsl:sort select="." order="ascending"/>
                    </xsl:apply-templates>
                </h2> 
                <br/>
                <h2>Niveau 4<br/>
                <!-- appel le template qui va afficher les mots de niveau 1-->
                    <xsl:apply-templates select="//mot[@niveau = $niveau4]">
                        <xsl:sort select="." order="ascending"/>
                    </xsl:apply-templates>
                </h2> 
                <br/>
                <h2>Niveau 5<br/>
                <!-- appel le template qui va afficher les mots de niveau 1-->
                    <xsl:apply-templates select="//mot[@niveau = $niveau5]">
                        <xsl:sort select="." order="ascending"/>
                    </xsl:apply-templates>
                </h2> 
                <br/>
            </body>
        </html>
    </xsl:template> 
    
    <xsl:template match="mot">   
        <ul>
            <li> <xsl:value-of select="."/></li>
        </ul>
    </xsl:template>   
    
    <!-- Version avec 1 foreach pour les niveaux et le reste en templates. 
    la les différents niveaux ne sont pas entré à la main mais il y a un foreach.-->
    
    <!--   
    <xsl:template match="/">
        <html>
            <head>
                <title>Page dictionnaire</title>
            </head>
            <body>
                <h1>Affichage des mots par ordre alphabétique et par niveau</h1> <br/>  
                <h2>
                  <xsl:for-each select="//mot[not(following-sibling::mot/@niveau = @niveau)]">
                    <xsl:call-template name="niveau">
                        <xsl:with-param name="numero" select="@niveau"/>
                    </xsl:call-template>
                  </xsl:for-each>      
               </h2>
            </body>
        </html>
    </xsl:template> 
    
    <xsl:template name="niveau">
        <xsl:param name="numero"/>
        <section>
            <h2>Niveau : <xsl:value-of select="$numero"/></h2> <br/>
            <ul>
                <xsl:apply-templates select="//mot[@niveau=$numero]">
                    <xsl:sort select="." order="ascending"/>
                </xsl:apply-templates>
            </ul>
        </section>
    </xsl:template>
    
    <xsl:template match="mot">   
        <ul>
            <li> <xsl:value-of select="."/></li>
        </ul>
    </xsl:template>   -->
    
    
</xsl:stylesheet>