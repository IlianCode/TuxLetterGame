<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : profil.xsl
    Created on : 6 décembre 2022, 11:02
    Author     : adomobia
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:tux="http://myGame/tux"  
                version="1.0"
                >
    <xsl:variable name="avatar" select="tux:profil/tux:avatar"/>
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Statistiques du joueur</title>
            </head>
            <body>
                <h1>Profil du joueur <br/>
                    <img src="$avatar"/>
                </h1>
                <h2>
                    nom : <xsl:value-of select="tux:profil/tux:nom"/>
                </h2>
                <h2>
                    date de naissance : <xsl:value-of select="tux:profil/tux:anniversaire"/>
                </h2>
                <h3>Récapitulatif des parties : <br/> <br/>
                    <table>
			<tr>
                            <th>Niveau</th>
                            <th>Mot</th>
                            <th>Temps</th>
                            <th>Score</th>
                            <th>Date</th>
			</tr>
                        <xsl:apply-templates select="//tux:profil/tux:parties/tux:partie"/>
                    </table>
                </h3>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="tux:partie">  
            <tr>
                <td>
                    <xsl:value-of select="tux:mot/@niveau"/>
                </td>
                <td>
                    <xsl:value-of select="tux:mot"/>
                </td>
                <td>
                    <xsl:value-of select="tux:temps"/>
                </td>
                <td>
                    <xsl:value-of select="@trouvé"/>
                </td>
                <td>
                     <xsl:value-of select="@date"/>
                </td>
            </tr>
    </xsl:template>   

</xsl:stylesheet>
