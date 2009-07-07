/*
 * USE - UML based specification environment
 * Copyright (C) 1999-2004 Mark Richters, University of Bremen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

// $Id$

package org.tzi.use.gui.views.diagrams;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tzi.use.gui.views.diagrams.classdiagram.EnumNode;
import org.tzi.use.uml.mm.MGeneralization;
import org.tzi.use.uml.ocl.type.EnumType;
import org.tzi.use.uml.sys.MSystem;

/**
 * This class provieds all Maps and Set which contain information about 
 * the layout in a classdiagram and objectdiagram.
 * 
 * @version     $ProjectVersion: 0.393 $
 * @author      Fabian Gutsche 
 */
public class LayoutInfos {

    private Map<?, BinaryEdge> fBinaryEdgeToEdgeMap;
    private Map<?, ? extends NodeBase> fNodeToNodeMap; 
    private Map<?, DiamondNode> fNaryEdgeToDiamondNodeMap;
    private Map<?, List<EdgeBase>> fNaryEdgeToHalfEdgeMap;
    private Map<?, NodeEdge> fEdgeNodeToEdgeMap;
    private Map<EnumType, EnumNode> fEnumToNodeMap;
    private Map<MGeneralization, GeneralizationEdge> fGenToGeneralizationEdge;
    private Set<Object> fHiddenNodes;
    private Set<Object> fHiddenEdges;
    
    private DiagramOptions fOpt;
    private DiagramView fDiagram;
    private MSystem fSystem;
    private PrintWriter fLog;
    
    private String fHiddenElementsXML;
    
    
    public LayoutInfos( Map<?, BinaryEdge> binaryEdgeToEdgeMap, 
                        Map<?, ? extends NodeBase> nodeToNodeMap, 
                        Map<?, DiamondNode> naryEdgeToDiamondNodeMap,
                        Map<?, List<EdgeBase>> naryEdgeToHalfEdgeMap,
                        Map<?, NodeEdge> edgeNodeToEdgeMap,
                        Map<EnumType, EnumNode> enumToNodeMap,
                        Map<MGeneralization, GeneralizationEdge> genToGeneralizationEdge,
                        Set<Object> hiddenNodes, Set<Object> hiddenEdges,
                        DiagramOptions opt, MSystem system,
                        DiagramView diagram, PrintWriter log ) {     
        fBinaryEdgeToEdgeMap = binaryEdgeToEdgeMap;
        fNodeToNodeMap = nodeToNodeMap;
        fNaryEdgeToDiamondNodeMap = naryEdgeToDiamondNodeMap;
        fNaryEdgeToHalfEdgeMap = naryEdgeToHalfEdgeMap;
        fEdgeNodeToEdgeMap = edgeNodeToEdgeMap;
        fEnumToNodeMap = enumToNodeMap;
        fGenToGeneralizationEdge = genToGeneralizationEdge;
        fHiddenNodes = hiddenNodes;
        fHiddenEdges = hiddenEdges;
        fOpt = opt;
        fDiagram = diagram;
        fSystem = system;
        fHiddenElementsXML = "";
        fLog = log;
    }

    public Map<?, BinaryEdge> getBinaryEdgeToEdgeMap() {
        return fBinaryEdgeToEdgeMap;
    }
    public Map<?, NodeEdge> getEdgeNodeToEdgeMap() {
        return fEdgeNodeToEdgeMap;
    }
    public Map<EnumType, EnumNode> getEnumToNodeMap() {
        return fEnumToNodeMap;
    }
    public Map<MGeneralization, GeneralizationEdge> getGenToGeneralizationEdge() {
        return fGenToGeneralizationEdge;
    }
    public Set<Object> getHiddenEdges() {
        return fHiddenEdges;
    }
    public void setHiddenEdges( Set<Object> hiddenEdges ) {
        fHiddenEdges = hiddenEdges;
    }
    public Set<Object> getHiddenNodes() {
        return fHiddenNodes;
    }
    public void setHiddenNodes( Set<Object> hiddenNodes ) {
        fHiddenNodes = hiddenNodes;
    }
    public Map<?, DiamondNode> getNaryEdgeToDiamondNodeMap() {
        return fNaryEdgeToDiamondNodeMap;
    }
    public Map<?, List<EdgeBase>> getNaryEdgeToHalfEdgeMap() {
        return fNaryEdgeToHalfEdgeMap;
    }
    public Map<?, ? extends NodeBase> getNodeToNodeMap() {
        return fNodeToNodeMap;
    }
    public DiagramOptions getOpt() {
        return fOpt;
    }
    public MSystem getSystem() {
        return fSystem;
    }

    public String getHiddenElementsXML() {
        return fHiddenElementsXML;
    }
    public void setHiddenElementsXML( String xml ) {
        fHiddenElementsXML = xml;
    }
    
    public DiagramView getDiagram() {
        return fDiagram;
    }
    
    public PrintWriter getLog() {
        return fLog;
    }
    
    
    public void resetNodesOnEdges() {
        if ( fBinaryEdgeToEdgeMap != null ) {
            for (BinaryEdge edge : fBinaryEdgeToEdgeMap.values()) {
                edge.resetNodesOnEdges();
            }
        }
    
        if ( fNaryEdgeToHalfEdgeMap != null ) {
            for (List<EdgeBase> edges : fNaryEdgeToHalfEdgeMap.values()) {
            	for (EdgeBase edge : edges) {
            		edge.resetNodesOnEdges();
            	}
            }
        }
        
        if ( fEdgeNodeToEdgeMap != null ) {
            for (EdgeBase edge : fEdgeNodeToEdgeMap.values()) {
                edge.resetNodesOnEdges();
            }
        }
        
        if ( fGenToGeneralizationEdge != null ) {
            for (EdgeBase edge : fGenToGeneralizationEdge.values()) {
                edge.resetNodesOnEdges();
            }
        }
    }
}
