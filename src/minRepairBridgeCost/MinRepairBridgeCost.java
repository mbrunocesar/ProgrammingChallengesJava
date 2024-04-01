package minRepairBridgeCost;

import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;

public class MinRepairBridgeCost {

    boolean debugMode;

    public MinRepairBridgeCost(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /*
        Time Complexity: O(n)
     */
    public boolean isIntactEdge(int[] edge, int[][] edgesToRepair) {
        for (int[] edgeToRepair : edgesToRepair) {
            if (edge[0] == edgeToRepair[0] && edge[1] == edgeToRepair[1]) {
                return false;
            }
        }

        return true;
    }

    /*
        Time Complexity: O(n log n)
     */
    public SortedSet<Integer> getConnectedNodes(int[][] edges, SortedSet<Integer> alreadyConnected) {
        SortedSet<Integer> currentSet = alreadyConnected != null ? alreadyConnected : new TreeSet<Integer>();
        SortedSet<Integer> anotherSet = new TreeSet<Integer>();
        for (int[] edge : edges) {
            if (currentSet.isEmpty()) {
                currentSet.add(edge[0]);
                currentSet.add(edge[1]);
            } else if (currentSet.contains(edge[0]) || currentSet.contains(edge[1])) {
                currentSet.add(edge[0]);
                currentSet.add(edge[1]);
            } else {
                anotherSet.add(edge[0]);
                anotherSet.add(edge[1]);
            }
        }

        Set<Integer> intersection = currentSet.stream()
                .filter(anotherSet::contains)
                .collect(Collectors.toSet());
        if (!intersection.isEmpty()) {
            currentSet.addAll(anotherSet);
        }

        return currentSet;
    }

    /*
        Time Complexity: O(n log n)
     */
    public SortedSet<Integer> getDisconnectedNodes(SortedSet<Integer> connectedNodes, int n) {
        SortedSet<Integer> disconnectedNodes = new TreeSet<Integer>();

        for (int i = 1; i < n + 1; i++) {
            if (!connectedNodes.contains(i)) {
                disconnectedNodes.add(i);
            }
        }

        return disconnectedNodes;
    }

    /*
        Time Complexity: O(m log n)
     */
    public int connectCheapest(SortedSet<Integer> connectedNodes, SortedSet<Integer> disconnectedNodes, int[][] edgesToRepair) {
        List<int[]> relevantBridges = new LinkedList<int[]>();
        for (int[] possibleBridge : edgesToRepair) {
            if (connectedNodes.contains(possibleBridge[0]) && disconnectedNodes.contains(possibleBridge[1])
                || connectedNodes.contains(possibleBridge[1]) && disconnectedNodes.contains(possibleBridge[0])
            ) {
                relevantBridges.add(possibleBridge);
            }
        }

        int cheapestValue = Integer.MAX_VALUE;
        int cheapestIndex = -1;
        for (int i = 0; i < relevantBridges.size(); i++) {
            int[] currentBridge = relevantBridges.get(i);
            if (currentBridge[2] < cheapestValue) {
                cheapestValue = currentBridge[2];
                cheapestIndex = i;
            }
        }

        connectedNodes.add(relevantBridges.get(cheapestIndex)[0]);
        connectedNodes.add(relevantBridges.get(cheapestIndex)[1]);

        return cheapestValue;
    }

    /*
        Time Complexity: O(n * m)
        - edges = n
        - edges to repair = m
     */
    public int minCostToRepairEdges(int n, int[][] edges, int[][] edgesToRepair) {
        int[][] intactEdges = new int[edges.length - edgesToRepair.length][2];
        int intactCount = 0;

        // Time Complexity of Block: O(n * m)
        for (int[] edge : edges) {
            if (isIntactEdge(edge, edgesToRepair)) {
                intactEdges[intactCount] = edge;
                intactCount++;
            }
        }

        // Time Complexity: O(n log n)
        SortedSet<Integer> reachableNodes = getConnectedNodes(intactEdges, null);

        int iteration = 0;
        int totalRepairCost = 0;

        // Time Complexity - worst case Scenario: O(log n)
        while (reachableNodes.size() != n) {
            // Time Complexity: O(n log n)
            SortedSet<Integer> disconnectedNodes = getDisconnectedNodes(reachableNodes, n);

            if (this.debugMode) {
                for (int node : reachableNodes) {
                    System.out.println("CONNECTED: " + node);
                }

                for (int node : disconnectedNodes) {
                    System.out.println("DISCONNECTED: " + node);
                }
            }

            // Time Complexity: O(m log n)
            totalRepairCost += connectCheapest(reachableNodes, disconnectedNodes, edgesToRepair);

            // Time Complexity: O(n log n)
            reachableNodes = getConnectedNodes(intactEdges, reachableNodes);
            if (this.debugMode) {
                for (int node : reachableNodes) {
                    System.out.println("NOW CONNECTED: " + node);
                }
            }

            iteration++;

            if (iteration > edgesToRepair.length) {
                break;
            }
        }

        return totalRepairCost;
    }

    public static void main(String[] args) {
        MinRepairBridgeCost minRepairer = new MinRepairBridgeCost(false);

        int n1 = 5;
        int[][] edges1 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
        int[][] edgesToRepair1 = {{1, 2, 12}, {3, 4, 30}, {1, 5, 8}};
        System.out.println(minRepairer.minCostToRepairEdges(n1, edges1, edgesToRepair1) ==  20);

        int n2 = 6;
        int[][] edges2 = {{1, 2}, {2, 3}, {4, 5}, {3, 5}, {1, 6}, {2, 4}};
        int[][] edgesToRepair2 = {{1, 6, 410}, {2, 4, 800}};
        System.out.println(minRepairer.minCostToRepairEdges(n2, edges2, edgesToRepair2) ==  410);

        int n3 = 6;
        int[][] edges3 = {{1, 2}, {2, 3}, {4, 5}, {5, 6}, {1, 5}, {2, 4}, {3, 4}};
        int[][] edgesToRepair3 = {{1, 5, 110}, {2, 4, 84}, {3, 4, 79}};
        System.out.println(minRepairer.minCostToRepairEdges(n3, edges3, edgesToRepair3) ==  79);

        // GRAPH DESIGNS ON DRAW.IO for conference:
        // https://app.diagrams.net/#G1HYG1zZef-3a_0GOTs0EZK9PJNIMkBOKj#%7B%22pageId%22%3A%22fpmm-GhUQqo6lT9EcdqC%22%7D
    }
}