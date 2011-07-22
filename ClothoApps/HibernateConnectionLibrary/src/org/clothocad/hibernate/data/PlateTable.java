/*
Copyright (c) 2009 The Regents of the University of California.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
SUCH DAMAGE.

THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
CALIFORNIA HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS..
 */
package org.clothocad.hibernate.data;
// Generated Jun 1, 2010 8:47:54 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.clothocore.api.data.ObjBase;
import org.clothocore.api.data.ObjBase.ObjBaseDatum;
import org.clothocore.api.data.ObjType;
import org.clothocore.api.data.Plate;
import org.clothocore.api.data.Plate.PlateDatum;
import org.clothocad.hibernate.hibernateDatum;

/**
 * PlateTable generated by hbm2java
 */
public class PlateTable implements java.io.Serializable, hibernateDatum {

    public static ObjType getType( Enum field ) {
        if ( field.getDeclaringClass() != Plate.Fields.class ) {
            return null;
        }

        Plate.Fields f = (Plate.Fields) field;

        switch ( f ) {
            case AUTHOR:
                return ObjType.PERSON;
            case PLATE_TYPE:
                return ObjType.PLATE_TYPE;
            case CONTAINERS:
                return ObjType.CONTAINER;
            default:
                return null;
        }
    }

    public PlateTable( Plate p ) {
        this.idPlate = p.getUUID();
        this.name = p.getName();
        this.dateCreated = p.getDateCreated();
        this.lastModified = p.getLastModified();
        this.barcode = p.getBarcode();
        this.location = p.getLocation();
        if ( p.getAuthor() != null ) {
            this.personTable = new PersonTable( p.getAuthor().getUUID() );
        }
        if ( p.getPlateType() != null ) {
            this.plateTypeTable = new PlateTypeTable( p.getPlateType().getUUID() );
        }
    }

    @Override
    public void runSecondaryProcessing(ObjBase obj) {
    }

    @Override
    public boolean needsSecondaryProcessing() {
        return false;
    }
    
    @Override
    public ObjBase getObject() {
        Plate.PlateDatum d = (PlateDatum) getObjBaseDatum();
        Plate p = new Plate( d );
        return p;
    }

    @Override
    public ObjBaseDatum getObjBaseDatum() {
        System.out.println( "Starting to retrieve plate " + idPlate );
        String[][] containerSet = new String[plateTypeTable.getNumRows() ][ plateTypeTable.getNumColumns() ];
        Iterator<hibernateDatum> containers = this.containerTables.iterator();

        while ( containers.hasNext() ) {
            ContainerTable ct = (ContainerTable) containers.next();
            short row = ct.getRow();
            short col = ct.getColumn();
            containerSet[row][col] = ct.getUUID();
        }

        Plate.PlateDatum d = new Plate.PlateDatum();
        d.uuid = idPlate;
        d.name = name;
        d.dateCreated = dateCreated;
        d.lastModified = lastModified;
        d._barcode = barcode;
        d._location = location;
        d._authorUUID = personTable.getIdPerson();
        d._plateTypeUUID = plateTypeTable.getIdPlateType();
        d.myWells = containerSet;
        return d;
    }

    @Override
    public String getUUID() {
        return idPlate;
    }

    public static String translate( Enum field ) {
        if ( field.getDeclaringClass() != Plate.Fields.class ) {
            return null;
        }

        Plate.Fields f = (Plate.Fields) field;

        switch ( f ) {
            case NAME:
                return "name";
            case DATE_CREATED:
                return "dateCreated";
            case LAST_MODIFIED:
                return "lastModifed";
            case BARCODE:
                return "barcode";
            case LOCATION:
                return "location";
            case AUTHOR:
                return "PersonTable";
            case PLATE_TYPE:
                return "plateTypeTable";
            case CONTAINERS:
                return "containerTables";
            default:
                return null;
        }
    }
    /***** AUTO-GENERATED CODE *****/
    private String idPlate;
    private PersonTable personTable;
    private PlateTypeTable plateTypeTable;
    private String name;
    private String barcode;
    private String location;
    private Date dateCreated;
    private Date lastModified;
    private Set containerTables = new HashSet( 0 );

    public PlateTable() {
    }

    public PlateTable( String idPlate ) {
        this.idPlate = idPlate;
    }

    public PlateTable( String idPlate, PersonTable personTable, PlateTypeTable plateTypeTable, String name, String barcode, String location, Date dateCreated, Date lastModified, Set containerTables ) {
        this.idPlate = idPlate;
        this.personTable = personTable;
        this.plateTypeTable = plateTypeTable;
        this.name = name;
        this.barcode = barcode;
        this.location = location;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
        this.containerTables = containerTables;
    }

    public String getIdPlate() {
        return this.idPlate;
    }

    public void setIdPlate( String idPlate ) {
        this.idPlate = idPlate;
    }

    public PersonTable getPersonTable() {
        return this.personTable;
    }

    public void setPersonTable( PersonTable personTable ) {
        this.personTable = personTable;
    }

    public PlateTypeTable getPlateTypeTable() {
        return this.plateTypeTable;
    }

    public void setPlateTypeTable( PlateTypeTable plateTypeTable ) {
        this.plateTypeTable = plateTypeTable;
    }

    public String getName() {
        return this.name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode( String barcode ) {
        this.barcode = barcode;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation( String location ) {
        this.location = location;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated( Date dateCreated ) {
        this.dateCreated = dateCreated;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public void setLastModified( Date lastModified ) {
        this.lastModified = lastModified;
    }

    public Set getContainerTables() {
        return this.containerTables;
    }

    public void setContainerTables( Set containerTables ) {
        this.containerTables = containerTables;
    }
}
