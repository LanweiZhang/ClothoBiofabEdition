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
import org.clothocore.api.core.Collector;
import org.clothocore.api.data.NucSeq;
import org.clothocore.api.data.NucSeq.NucSeqDatum;
import org.clothocore.api.data.ObjBase;
import org.clothocore.api.data.ObjBase.ObjBaseDatum;
import org.clothocore.api.data.ObjType;
import org.clothocad.hibernate.hibernateDatum;
import org.clothocad.hibernate.HibernateConnection;

/**
 * NucseqTable generated by hbm2java
 */
public class NucseqTable implements java.io.Serializable, hibernateDatum {

    public static ObjType getType( Enum field ) {
        if ( field.getDeclaringClass() != NucSeq.Fields.class ) {
            return null;
        }

        NucSeq.Fields f = (NucSeq.Fields) field;

        switch ( f ) {
            case VECTORS:
                return ObjType.VECTOR;
            case PARTS:
                return ObjType.PART;
            case ANNOTATIONS:
                return ObjType.ANNOTATION;
            case FEATURES:
                return ObjType.FEATURE;
            default:
                return null;
        }
    }

    public NucseqTable( NucSeq n ) {
        this.idNucseq = n.getUUID();
        this.sequence = n.getSeq();
        this.dateCreated = n.getDateCreated();
        this.lastmodified = n.getLastModified();
        this.isLocked = n.isLocked();

        if(!n.isInDatabase()) {
            needsSecondaryProcessing = true;
            return;
        }

        HashSet<String> existingLinks = new HashSet<String>();

        //Remove old annotations
        String query = "from NucseqAnnotation where nucseqId='" + idNucseq + "'";
        Iterator xrefs = HibernateConnection.connection.query( query );
        while ( xrefs.hasNext() ) {
            NucseqAnnotation cx = (NucseqAnnotation) xrefs.next();
            String uuid = cx.getAnnotationId();
            existingLinks.add( uuid );
            if ( !n.getAnnotationLinks().contains( uuid ) ) {
                HibernateConnection.connection.deleteDatum( cx );
            }
        }

        //Save all the new annotations
        for ( String uuid : n.getAnnotationLinks() ) {
            if ( !existingLinks.contains( uuid ) ) {
                NucseqAnnotation cxr = new NucseqAnnotation( Collector.getAnnotation( uuid ) );
                this.nucseqAnnotations.add( cxr );
                HibernateConnection.connection.saveDatum( cxr );
            }
        }
    }

    @Override
    public void runSecondaryProcessing(ObjBase obj) {
        NucSeq n = (NucSeq) obj;
        //Save all the new annotations
        for ( String uuid : n.getAnnotationLinks() ) {
            NucseqAnnotation cxr = new NucseqAnnotation( Collector.getAnnotation( uuid ) );
            this.nucseqAnnotations.add( cxr );
            HibernateConnection.connection.saveDatum( cxr );
        }
        needsSecondaryProcessing = false;
    }

    @Override
    public boolean needsSecondaryProcessing() {
        return this.needsSecondaryProcessing;
    }

    @Override
    public ObjBase getObject() {
        NucSeq.NucSeqDatum d = (NucSeqDatum) getObjBaseDatum();
        NucSeq n = new NucSeq( d );
        return n;
    }

    @Override
    public ObjBaseDatum getObjBaseDatum() {
        HashSet<String> annotations = new HashSet<String>();
        Iterator<hibernateDatum> annots = this.nucseqAnnotations.iterator();

        while ( annots.hasNext() ) {
            NucseqAnnotation ct = (NucseqAnnotation) annots.next();
            annotations.add( ct.getUUID() );
        }

        NucSeq.NucSeqDatum d = new NucSeq.NucSeqDatum();
        d.uuid = idNucseq;
        d.name = "nucseq";
        d.dateCreated = dateCreated;
        d.lastModified = lastmodified;
        d.isLocked = isLocked;
        d.annotations = annotations;
        d.theSequence = sequence;
        return d;
    }

    @Override
    public String getName() {
        return "nucseq";
    }

    @Override
    public String getUUID() {
        return idNucseq;
    }

    @Override
    public Date getLastModified() {
        return new Date();
    }

    public static String translate( Enum field ) {
        if ( field.getDeclaringClass() != NucSeq.Fields.class ) {
            return null;
        }

        NucSeq.Fields f = (NucSeq.Fields) field;

        switch ( f ) {
            case NAME:
                return "name";
            case DATE_CREATED:
                return "dateCreated";
            case LAST_MODIFIED:
                return "lastModifed";
            case SEQUENCE:
                return "sequence";

            case VECTORS:
                return "vectorTables";
            case PARTS:
                return "partTables";
            case ANNOTATIONS:
                return "nucseqAnnotations";
            case FEATURES:
                return "featureTables";
            case OLIGOS:
                return "oligoTables";
            default:
                return null; 
        }
    }

    private boolean needsSecondaryProcessing = false;
    /***** AUTO-GENERATED CODE *****/
    private String idNucseq;
    private String sequence;
    private Boolean isLocked;
    private Date dateCreated;
    private Date lastmodified;
    private Set vectorTables = new HashSet( 0 );
    private Set nucseqAnnotations = new HashSet( 0 );
    private Set partTables = new HashSet( 0 );
    private Set featureTables = new HashSet( 0 );
    private Set oligoTables = new HashSet( 0 );

    public NucseqTable() {
    }

    public NucseqTable( String idNucseq ) {
        this.idNucseq = idNucseq;
    }

    public NucseqTable( String idNucseq, String sequence, Boolean isLocked, Date dateCreated, Date lastmodified, Set vectorTables, Set nucseqAnnotations, Set partTables, Set featureTables, Set oligoTables ) {
        this.idNucseq = idNucseq;
        this.sequence = sequence;
        this.isLocked = isLocked;
        this.dateCreated = dateCreated;
        this.lastmodified = lastmodified;
        this.vectorTables = vectorTables;
        this.nucseqAnnotations = nucseqAnnotations;
        this.partTables = partTables;
        this.featureTables = featureTables;
        this.oligoTables = oligoTables;
    }

    public String getIdNucseq() {
        return this.idNucseq;
    }

    public void setIdNucseq( String idNucseq ) {
        this.idNucseq = idNucseq;
    }

    public String getSequence() {
        return this.sequence;
    }

    public void setSequence( String sequence ) {
        this.sequence = sequence;
    }

    public Boolean getIsLocked() {
        return this.isLocked;
    }

    public void setIsLocked( Boolean isLocked ) {
        this.isLocked = isLocked;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated( Date dateCreated ) {
        this.dateCreated = dateCreated;
    }

    public Date getLastmodified() {
        return this.lastmodified;
    }

    public void setLastmodified( Date lastmodified ) {
        this.lastmodified = lastmodified;
    }

    public Set getVectorTables() {
        return this.vectorTables;
    }

    public void setVectorTables( Set vectorTables ) {
        this.vectorTables = vectorTables;
    }

    public Set getNucseqAnnotations() {
        return this.nucseqAnnotations;
    }

    public void setNucseqAnnotations( Set nucseqAnnotations ) {
        this.nucseqAnnotations = nucseqAnnotations;
    }

    public Set getPartTables() {
        return this.partTables;
    }

    public void setPartTables( Set partTables ) {
        this.partTables = partTables;
    }

    public Set getFeatureTables() {
        return this.featureTables;
    }

    public void setFeatureTables( Set featureTables ) {
        this.featureTables = featureTables;
    }

    public Set getOligoTables() {
        return this.oligoTables;
    }

    public void setOligoTables( Set oligoTables ) {
        this.oligoTables = oligoTables;
    }
}
