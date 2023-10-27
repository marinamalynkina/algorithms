//package trees;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.PriorityQueue;
//
//class T2458 {
//
//    class NodeInfo {
//        public int v;
//        public int maxLevel;
//    }
//
//    HashMap<Integer, Integer> nodesLevel = new HashMap<Integer, Integer>();
//    ArrayList<PriorityQueue<NodeInfo>> levels = new ArrayList<>();
//
//    public int[] treeQueries(TreeNode root, int[] queries) {
//        if (root == null) return new int[1];
//        getMaxLevel(root, 0);
//        int[] result = new int[queries.length];
//        for (int i = 0; i < queries.length; i++) {
//            int q = queries[i];
//            Integer level = nodesLevel.get(q);
//            if (level != null) {
//                PriorityQueue<NodeInfo> maxHeights = levels.get(level.intValue());
//                NodeInfo peek = maxHeights.peek();
//                if (peek.v != q) {
//                    result[i] = peek.maxLevel;
//                } else if (maxHeights.size() >= 2) {
//                    NodeInfo second = (NodeInfo)maxHeights.toArray()[1];
//                    if (second.v != q) result[i] = second.maxLevel;
//                } else {
//                    result[i] = level-1;
//                }
//            }
//        }
//        return result;
//    }
//
//    private int getMaxLevel(TreeNode root, int level) {
//        if (root == null) return level - 1;
//        nodesLevel.put(root.val, level);
//        if (levels.size() == level) {
//            levels.add(new PriorityQueue<NodeInfo>(new NodeComparator()));
//        }
//        int max = Math.max(getMaxLevel(root.left, level+1), getMaxLevel(root.right, level+1));
//        NodeInfo newnode = new NodeInfo();
//        newnode.v = root.val;
//        newnode.maxLevel = max;
//        levels.get(level).add(newnode);
//        return max;
//    }
//
//    private class NodeComparator implements Comparator<NodeInfo> {
//        @Override
//        public int compare(NodeInfo x, NodeInfo y) {
//            if (x.maxLevel < y.maxLevel) {
//                return -1;
//            }
//            if (x.maxLevel > y.maxLevel) {
//                return 1;
//            }
//            return 0;
//        }
//    }
//}
