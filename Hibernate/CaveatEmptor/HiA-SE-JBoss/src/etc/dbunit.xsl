<?xml version="1.0"?>
<!--

Transforms Hibernate 3.x XML mapping files into DBUnit FlatXML dataset templates.
Doesn't handle unidirectional *-to-many associations and only supports normalized
inheritance mappings.

christian@hibernate.org

 -->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <dataset>
            <xsl:apply-templates/>
        </dataset>
    </xsl:template>

    <xsl:template match="//class|//joined-subclass">
        <xsl:element name="{@table}">
            <xsl:apply-templates select="*[not(self::joined-subclass)]"/>
        </xsl:element>
        <xsl:apply-templates select="joined-subclass"/>
    </xsl:template>

    <xsl:template match="id|property|discriminator|version|timestamp|many-to-one|key">
        <xsl:choose>
            <xsl:when test="string(@column)">
                <xsl:attribute name="{@column}">
                    <xsl:call-template name="scalarvalue">
                        <xsl:with-param name="classname" select="@class"/>
                        <xsl:with-param name="coltype" select="@type"/>
                    </xsl:call-template>
                </xsl:attribute>
            </xsl:when>
            <xsl:otherwise>
                <xsl:apply-templates select="child::column"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="column">
        <xsl:attribute name="{@name}">
            <xsl:call-template name="scalarvalue">
                <xsl:with-param name="classname" select="../@class"/>
                <xsl:with-param name="coltype" select="../@type"/>
            </xsl:call-template>
        </xsl:attribute>
    </xsl:template>

    <xsl:template name="scalarvalue">
        <xsl:param name="classname"/>
        <xsl:param name="coltype"/>
        <xsl:variable name="type" select="translate($coltype,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')"/>
        <xsl:choose>
            <xsl:when test="contains($type, 'STRING')">TESTDATA</xsl:when>
            <xsl:when test="contains($type, 'TIMESTAMP')">2001-01-01 00:00:00</xsl:when>
            <xsl:when test="contains($type, 'DATE')">2001-01-01</xsl:when>
            <xsl:when test="contains($type, 'LONG') or contains($type, 'INT') or contains($type, 'SHORT')">123</xsl:when>
            <xsl:when test="contains($type, 'BOOL')">false</xsl:when>
            <xsl:otherwise>
                <xsl:choose>
                    <xsl:when test="string($coltype)">
                        <xsl:value-of select="$coltype"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text>(ID)</xsl:text><xsl:value-of select="$classname"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="text()"/>

</xsl:stylesheet>
