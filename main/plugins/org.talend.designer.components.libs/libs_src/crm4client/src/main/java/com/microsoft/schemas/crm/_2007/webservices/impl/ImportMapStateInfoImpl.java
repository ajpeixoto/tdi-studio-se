/*
 * XML Type:  ImportMapStateInfo
 * Namespace: http://schemas.microsoft.com/crm/2007/WebServices
 * Java type: com.microsoft.schemas.crm._2007.webservices.ImportMapStateInfo
 *
 * Automatically generated - do not modify.
 */
package com.microsoft.schemas.crm._2007.webservices.impl;

import org.apache.xmlbeans.StringEnumAbstractBase;

/**
 * An XML ImportMapStateInfo(@http://schemas.microsoft.com/crm/2007/WebServices).
 *
 * This is an atomic type that is a restriction of com.microsoft.schemas.crm._2007.webservices.ImportMapStateInfo.
 */
public class ImportMapStateInfoImpl extends org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx implements com.microsoft.schemas.crm._2007.webservices.ImportMapStateInfo
{
    
    public ImportMapStateInfoImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType, true);
    }
    
    protected ImportMapStateInfoImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
    {
        super(sType, b);
    }

    @Override
    public StringEnumAbstractBase enumValue() {
        return getEnumValue();
    }

    @Override
    public void set(StringEnumAbstractBase e) {
        setEnumValue(e);
    }
    
    private static final javax.xml.namespace.QName FORMATTEDVALUE$0 = 
        new javax.xml.namespace.QName("", "formattedvalue");
    
    
    /**
     * Gets the "formattedvalue" attribute
     */
    public java.lang.String getFormattedvalue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(FORMATTEDVALUE$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "formattedvalue" attribute
     */
    public org.apache.xmlbeans.XmlString xgetFormattedvalue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(FORMATTEDVALUE$0);
            return target;
        }
    }
    
    /**
     * True if has "formattedvalue" attribute
     */
    public boolean isSetFormattedvalue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(FORMATTEDVALUE$0) != null;
        }
    }
    
    /**
     * Sets the "formattedvalue" attribute
     */
    public void setFormattedvalue(java.lang.String formattedvalue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(FORMATTEDVALUE$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(FORMATTEDVALUE$0);
            }
            target.setStringValue(formattedvalue);
        }
    }
    
    /**
     * Sets (as xml) the "formattedvalue" attribute
     */
    public void xsetFormattedvalue(org.apache.xmlbeans.XmlString formattedvalue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(FORMATTEDVALUE$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(FORMATTEDVALUE$0);
            }
            target.set(formattedvalue);
        }
    }
    
    /**
     * Unsets the "formattedvalue" attribute
     */
    public void unsetFormattedvalue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(FORMATTEDVALUE$0);
        }
    }
}