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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import org.clothocore.api.data.Comment;
import org.clothocore.api.data.Comment.CommentDatum;
import org.clothocore.api.data.ExpData;
import org.clothocore.api.data.ExpData.ExpDatum;
import org.clothocore.api.data.ObjBase;
import org.clothocore.api.data.ObjBase.ObjBaseDatum;
import org.clothocore.api.data.ObjType;
import org.clothocore.api.data.SampleData;
import org.clothocore.api.data.SampleData.SampleDataDatum;
import org.clothocore.api.data.SequenceRead;
import org.clothocore.api.data.SequenceRead.SequenceReadDatum;
import org.clothocad.hibernate.hibernateDatum;

/**
 * SampleDataTable generated by hbm2java
 */
public class SampleDataTable implements java.io.Serializable, hibernateDatum {

    public static ObjType getType( Enum field ) {
        throw new UnsupportedOperationException( "Not yet implemented" );
    }

    public SampleDataTable( SampleData s ) {
        this.idSampleData = s.getUUID();
        this.name = s.getName();
        this.dateCreated = s.getDateCreated();
        this.lastModified = s.getLastModified();
        SampleData.dataType type = s.getDatatype();
        this.dataType = type.toString();
        this.personTable = new PersonTable( s.getAuthor().getUUID() );

        switch ( type ) {
            case SEQUENCE_READ:
                SequenceRead sr = (SequenceRead) s;
                if ( sr.getABITrace() != null ) {
                    this.attachmentTable = new AttachmentTable( sr.getABITrace().getUUID() );
                }
                this.freeString1 = sr.getBarcode();
                this.freeString2 = sr.getOligo().getUUID();
                SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy HH:mm:ss", Locale.US );
                this.freeString3 = sdf.format( sr.getDateSubmitted() );
                if(sr.getResultWiki()!=null) {
                    this.wikitextTable = new WikitextTable( sr.getResultWiki().getUUID() );
                }
                break;
            case COMMENT:
                Comment com = (Comment) s;
                this.wikitextTable = new WikitextTable( com.getWikiText().getUUID() );
                break;
            case EXP_DATA:
                ExpData exd = (ExpData) s;
                this.freeString1 = exd.getRawData();
                this.freeString2 = exd.getExperimentSet();
            default:
                break;
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
        SampleData.dataType type = SampleData.dataType.valueOf( dataType );
        SampleData.SampleDataDatum d = (SampleDataDatum) getObjBaseDatum();
        HashSet<String> datalist = new HashSet<String>();
        Iterator<hibernateDatum> objs = this.sampleDataXrefs.iterator();
        while ( objs.hasNext() ) {
            SampleDataXref cx = (SampleDataXref) objs.next();
            String uuid = cx.getId().getSampleId();
            d.sampleLinks.add( uuid );
        }

        switch ( type ) {
            case SEQUENCE_READ:
                return new SequenceRead((SequenceReadDatum) d);
            case COMMENT:
                return new Comment((CommentDatum) d);
            case EXP_DATA:
                return new ExpData((ExpDatum) d);
            default:
                return null;
        }
    }

    @Override
    public ObjBaseDatum getObjBaseDatum() {
        SampleData.dataType type = SampleData.dataType.valueOf( dataType );
        SampleData.SampleDataDatum d = null;
        switch ( type ) {
            case SEQUENCE_READ:
                SequenceRead.SequenceReadDatum srd = new SequenceRead.SequenceReadDatum();

                SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy HH:mm:ss", Locale.US );
                Date dateSubmitted = null;
                try {
                    dateSubmitted = sdf.parse( freeString3 );
                } catch ( Exception ex ) {
                }

                srd._dateSubmitted = dateSubmitted;
                if(this.attachmentTable!=null) {
                    srd._abiUUID = this.attachmentTable.getIdAttachment();
                }
                srd._barcode = this.freeString1;
                srd._oligoUUID = this.freeString2;
                if(this.wikitextTable!=null) {
                    srd._resultWikiUUID = this.wikitextTable.getIdWikitext();
                }

                d = srd;
                break;
            case COMMENT:
                Comment.CommentDatum cd = new Comment.CommentDatum();
                cd._wikiUUID = this.wikitextTable.getIdWikitext();
                d = cd;
                break;
            case EXP_DATA:
                ExpData.ExpDatum eed = new ExpData.ExpDatum();
                eed._xmlText = this.freeString1;
                eed._experimentSet = this.freeString2;
                d = eed;
                break;
            default:
                return null;
        }

        d.uuid = idSampleData;
        d.name = name;
        d.dateCreated = dateCreated;
        d.lastModified = lastModified;
        d._authorUUID = personTable.getIdPerson();
        return d;
    }

    @Override
    public String getUUID() {
        return idSampleData;
    }

    public static String translate( Enum field ) {
        if ( field.getDeclaringClass() != SampleData.Fields.class ) {
            return null;
        }

        SampleData.Fields f = (SampleData.Fields) field;

        switch ( f ) {
            case NAME:
                return "name";
            case DATE_CREATED:
                return "dateCreated";
            case LAST_MODIFIED:
                return "lastModifed";
            case DATA_TYPE:
                return "dataType";
            case AUTHOR:
                return "authorId";
            default:
                return null;
        }
    }
    /***** AUTO-GENERATED CODE *****/
    private String idSampleData;
    private AttachmentTable attachmentTable;
    private PersonTable personTable;
    private WikitextTable wikitextTable;
    private String name;
    private String dataType;
    private Date dateCreated;
    private Date lastModified;
    private String freeString1;
    private String freeString2;
    private String freeString3;
    private String freeString4;
    private Set sampleDataXrefs = new HashSet( 0 );

    public SampleDataTable() {
    }

    public SampleDataTable( String idSampleData ) {
        this.idSampleData = idSampleData;
    }

    public SampleDataTable( String idSampleData, AttachmentTable attachmentTable, PersonTable personTable, WikitextTable wikitextTable, String name, String dataType, Date dateCreated, Date lastModified, String freeString1, String freeString2, String freeString3, String freeString4, Set sampleDataXrefs ) {
        this.idSampleData = idSampleData;
        this.attachmentTable = attachmentTable;
        this.personTable = personTable;
        this.wikitextTable = wikitextTable;
        this.name = name;
        this.dataType = dataType;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
        this.freeString1 = freeString1;
        this.freeString2 = freeString2;
        this.freeString3 = freeString3;
        this.freeString3 = freeString4;
        this.sampleDataXrefs = sampleDataXrefs;
    }

    public String getIdSampleData() {
        return this.idSampleData;
    }

    public void setIdSampleData( String idSampleData ) {
        this.idSampleData = idSampleData;
    }

    public AttachmentTable getAttachmentTable() {
        return this.attachmentTable;
    }

    public void setAttachmentTable( AttachmentTable attachmentTable ) {
        this.attachmentTable = attachmentTable;
    }

    public PersonTable getPersonTable() {
        return this.personTable;
    }

    public void setPersonTable( PersonTable personTable ) {
        this.personTable = personTable;
    }

    public WikitextTable getWikitextTable() {
        return this.wikitextTable;
    }

    public void setWikitextTable( WikitextTable wikitextTable ) {
        this.wikitextTable = wikitextTable;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType( String dataType ) {
        this.dataType = dataType;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated( Date dateCreated ) {
        this.dateCreated = dateCreated;
    }

    @Override
    public Date getLastModified() {
        return this.lastModified;
    }

    public void setLastModified( Date lastModified ) {
        this.lastModified = lastModified;
    }

    public String getFreeString1() {
        return this.freeString1;
    }

    public void setFreeString1( String freeString1 ) {
        this.freeString1 = freeString1;
    }

    public String getFreeString2() {
        return this.freeString2;
    }

    public void setFreeString2( String freeString2 ) {
        this.freeString2 = freeString2;
    }

    public String getFreeString3() {
        return this.freeString3;
    }

    public void setFreeString3( String freeString3 ) {
        this.freeString3 = freeString3;
    }

    public String getFreeString4() {
        return this.freeString4;
    }

    public void setFreeString4( String freeString4 ) {
        this.freeString4 = freeString4;
    }
    public Set getSampleDataXrefs() {
        return this.sampleDataXrefs;
    }

    public void setSampleDataXrefs( Set sampleDataXrefs ) {
        this.sampleDataXrefs = sampleDataXrefs;
    }


}
