/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clothocad.algorithm.seqanalyzer.sequencing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author benjaminbubenheim
 */
public class jcatest {

  public static void main(String[] args){
        ABITrace abi1=null, abi2=null, abi3=null, abi4=null, abi5=null;
        ArrayList<ABITrace> abis=new ArrayList<ABITrace>();
        String actual="GAATTCATGAGATCTCAGCTGAAGTGACCGGATTAGCAACGCAGTAACCCGAAATCCTCTTTGACAAAGACAAAGCGTGTCAGGCTGATTCTGATGCGCTTTTTTTTGAAATGTCACAAAAATTCCATGCGGGAGATGAGAGCTAAAATCCCCGTGCAGAACTTTCCACCCAGGGCGAGAAAACTTGTCATTTTGACCTGTTCGCCCTTCGGAACAGTCGAAATCGATCGCGCATCGCTTTCGTGCATAGTTATGCAGCCCTCTAAAAACGATTCTGGCGCGTTTTTCTGGTTTGGCCTGGTGTTTTCTTGTCTTTTTGCGTTTTTTGCGCCAGAAAGCGACTGAGGGCGTTTTAAGGGGTACGTACAACGGGAGTTATGGTAAATGGATCGGGTTTTCGGGAAGGTTCGACAGGATTTGCCGTTGGGTGTAGTGTAAGCGACTGAAAAACAAACGCCCCTGAAATCATGTCCAGATCCGGCAAGATTCAACATGAAATACAGAGGGCGTGTAGGTCGCATAACCCGGAGAGAATCGTAACACATGAGCGCCGCGCTTCAATACTTCGACGAAAATTTACCCCATCGTCCCTATCACACGGATGATCTCGCTTTTGGTCTTCGCATCTCCGGCAAAGGGCGTGCGCTTCTTGCGCGGTACATACAGCAGAACCAGCCTCATGCGCAGTTCTGGCTGGTTTTTGATGTTGACCGCGAGGGGGCTGCGATTGACTGGAGCGACCTGAACGCCCCCGCACCGAACATCACCATAAAAAATCCCGTGAACGGTCACGCCCACCTGCTCTACGCACTCAATATCGCCGTGAGAACCGCGCCTGATGCTTCGGTTAAGGCGCTGAAATACGCCGCCGCGATTGAACGTGCGTTGTGTGAAAAACTGGGCGCAGATGTAAACTACAGCGGCCTGATTTGCAAAAATCCGTTCCATCTGGAATGGCAGGTGATGGAGTGGCGTGAGGAAGCCTATACCCTCGATGAACTGGCTGATTATCTTGATTTGAGCACTTCAGCGCGCCGTAGCATCGATAAACATTACGGGATGGGGCGAAACTGCCATCTGTTCGAAATGACGCGCAAATGGGCTTACAGGGCGATTCGTCAGGGCTGGCCAGCATTCTCACAGTGGCTTGATGCCGTGATTCAGCGTGTCGAAATGTACAACGCATCGCTTCCCGTTCCGCTTTCACCTCCTGAATGTCGGGCTATTGGCAAGAGTATTGCGAAATACACGCACAGGAACTTCACGGCGGAAACTTTCGCACAGTATGTGGCTGATACGCACACGCCAGAAATACAGGCCAAGAGAGGCAGGAAAGGTGGCATCGCTAAAGGCGAAGCCTACGATGACAAGCGTTTCATGGCGCTATGTATGCTGGAGAATGGATATTCTCAGAAAGCTATTGCGGCGATGTTGGAGGTTTCTACTCGAACCATTCGAAACTGGAAAAGCGGAAAATAGGGATCC";
        String actual1="GAATTCATGAGATCTTGTTCGGAGCCGCTTTAACCCACTCTGTGGAAGTGCTGGATCC";
        String actual2="GAATTCATGAGATCTAGAACCGCAACTCCCAATAAACGCAAACCCAAAACTCCAACGGATAATCGCTGATGGCTGCTAGACCCCGATCTCACAAAATCTCTATACCCAATTTATATTGCAAATTAGATAAGCGAACCGGAAAGGTATATTGGCAATACAAACATCCACTATCCGGTCGTTTTCATAGCTTAGGAACTGATGAGAATGAAGCAAAACAAGTTGCTACTGAAGCAAATACCATTATTGCTGAACAACGTACCAGACAAATATTAAGCGTCAATGAGCGTCTGGAAAGAATGAAAGGCAGGCGCTCAGACATTACGGTGACAGAATGGCTTGATAAATATATTTCTATCCAGGAGGACAGGCTGCAACATAATGAACTAAGACCCAACTCCTATCGGCAAAAAGGCAAACCCATTCGTCTTTTCCGTGAGCATTGTGGAATGCAACACCTCAAGGATATTACCGCACTTGATATTGCCGAAATAATTGATGCTGTAAAGGCTGAAGGTCATAACAGGATGGCGCAAGTCGTGAGAATGGTGTTGATCGACGTCTTCAAAGAAGCACAACACGCAGGACATGTTCCGCCAGGATTTAACCCAGCGCAGGCAACAAAACAACCGCGAAATCGAGTAAACCGCCAAAGATTGTCACTGCTCGAATGGCAGGCAATATTTGAAAGCGTAAGCAGACGGCAGCCCTATTTAAAATGCGGCATGCTACTTGCTCTTGTTACTGGACAACGTTTAGGCGATATCTGCAATTTGAAATTCTCTGATATATGGGACGACATGTTGCACATTACTCAGGAAAAAACCGGTTCAAAACTTGCTATTCCGCTTAACCTGAAATGCGATGCTCTGAATATTACCCTTCGTGAAGTTATATCTCAGTGCAGGGATGCTGTTGTTAGTAAATATCTGGTCCATTACCGTCACACTACCTCTCAAGCAAACAGAGGAGACCAGGTTTCTGCAAATACTCTGACAACGGCTTTTAAAAAGGCCAAGGAAAAATGTGGCATAAAATGGGAGCAAGGAACTGCGCCCACATTTCATGAACAGCGATCTCTGTCAGAACGATTATATCGGGAACAGGGTCTGGATACGCAAAAGTTGTTAGGCCATAAATCCAGAAAAATGACCGACCGATACAATGATGATCGTGGTAAAGACTGGATTATCGTAGATATCAAAACAGCATAGGGATCC";
        try{
        abi1=new ABITrace(new File("BG4_ca998_2010-03-20_B03_029.ab1"));
        abi2=new ABITrace(new File("BG4_G00101_2010-03-19_E10_072.ab1"));

        abi3=new ABITrace(new File("CF8_ca998_2010-03-25_C09_075.ab1"));

        abi4=new ABITrace(new File("CC4_ca998_2010-03-27_A10_080.ab1"));
        abi5=new ABITrace(new File("CC4_G00101_2010-03-26_C02_012.ab1"));

        }
        catch(IOException e){
            System.out.println("OOPS");
        }
        abis.add(abi1);
        abis.add(abi2);

        seqAnalysis s=new seqAnalysis(abis, actual, null, null, true);




    }
}
