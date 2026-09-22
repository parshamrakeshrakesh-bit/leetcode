class Solution {
    static class Node {
        int product;
        int[] cnt;
        Node(int k) {
            cnt = new int[k];
        }
    }
    int n, k;
    Node[] tree;
    Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;
        Node res = new Node(k);
        res.product = (left.product * right.product) % k;
        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
        }
        for (int a = 0; a < k; a++) {
            if (left.product == a) {
                for (int b = 0; b < k; b++) {
                    int rem = (a * b) % k;
                    res.cnt[rem] += right.cnt[b];
                }
            }
        }
        return res;
    }
    void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            tree[node] = new Node(k);
            int rem = nums[l] % k;
            tree[node].product = rem;
            tree[node].cnt[rem] = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }
    void update(int node, int l, int r, int index, int value) {
        if (l == r) {
            tree[node] = new Node(k);
            int rem = value % k;
            tree[node].product = rem;
            tree[node].cnt[rem] = 1;
            return;
        }
        int mid = (l + r) / 2;
        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }
    Node query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) {
            return null;
        }
        if (ql <= l && r <= qr) {
            return tree[node];
        }
        int mid = (l + r) / 2;
        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);
        return merge(left, right);
    }
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        tree = new Node[4 * n];
        build(1, 0, n - 1, nums);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            update(1, 0, n - 1, index, value);
            Node res = query(1, 0, n - 1, start, n - 1);
            ans[i] = res.cnt[x];
        }
        return ans;
    }
}