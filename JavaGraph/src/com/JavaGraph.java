/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ratan
 */
public class JavaGraph {
    static class Graph {
        int V;
        LinkedList<Integer> adjListArray[];
        Graph(int V) {
            this.V = V;
            adjListArray = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adjListArray[i] = new LinkedList<>();
            }
        }
    }

    /**
     * 
     * @param graph
     * @param src
     * @param dest 
     */
    static void addEdge(Graph graph, int src, int dest) {
        // Add an edge from src to dest. 
        graph.adjListArray[src].addFirst(dest);
    }

    static List<Integer> prev;
    
    public static List<Integer> findSource(Graph graph){
    	List<Integer> list = new ArrayList<>();
    	for (int outerIndex = 0; outerIndex < graph.V; outerIndex++) {
    		boolean found = false;
    		for (int innerIndex = 0; innerIndex < graph.V; innerIndex++) {
        		if(outerIndex != innerIndex && graph.adjListArray[innerIndex].indexOf(outerIndex) != -1){
        			found = true;
        			break;
        		}
        	}
    		if(found == false){
    			list.add(outerIndex);
    		}
    	}
		return list;
    	
    }
    
    /**
     * 
     * @param graph 
     */
    static void printGraph(Graph graph) {
    	List<Integer> sourcesList = findSource(graph);    	
        for (int v = 0; v < sourcesList.size(); v++) {
            prev = new ArrayList<>();
            printAdjList(sourcesList.get(v), graph);
        }
    }

    static void printAdjList(int index, Graph graph) {
        prev.add(index);
        for (Integer pCrawl : graph.adjListArray[index]) {
            if (graph.adjListArray[pCrawl].size() != 0) {
                if (prev.size() > 1) {
                    prev.remove(prev.size() - 1);
                }
                printAdjList(pCrawl, graph);
            } else {
                for (int i : prev) {
                    System.out.print(i + " --> ");
                }
                System.out.println(pCrawl);
            }
        }
        if (graph.adjListArray[index].size() == 0) {
        	System.out.println(index);
        }
    }

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {

        StringBuilder builder = new StringBuilder();
        try (FileReader fileRead = new FileReader("graph.txt")) {
            int index;
            while ((index = fileRead.read()) != -1) {
                builder.append((char) index);
            }
        }
        String[] eachLine = builder.toString().split("\n");
        int V = Integer.valueOf(eachLine[0].trim());
        Graph graph = new Graph(V);

        for (int index = 0; index < eachLine.length; index++) {
            if (index != 0) {
                String[] vertex = eachLine[index].split(",");
                addEdge(graph, Integer.valueOf(vertex[0].trim()), Integer.valueOf(vertex[1].trim()));
            }
        }
        printGraph(graph);
    }
}
