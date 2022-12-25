package unionfind;

import java.util.*;

public class UnionFindLeet {
    class UF {
        int[] root;
        int[] weight;
        public UF(int size) {
            root = new int[size];
            for(int i =0; i < size; i++) {
                root[i] = i;
            }
        }
        public int find(int ele) {
            if(root[ele] != ele)
                root[ele] = find(root[ele]); //minimizes traversing next time
            return root[ele];
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if(px != py) {
                if(weight[px] > weight[py]) {
                    //px has bigger connection
                    root[py] = px; // make py use that
                } else if(weight[px] < weight[py]){
                    root[px] = py;
                } else {
                    root[py] = px;
                    weight[px]++;
                }
            }
        }

        // 721. Accounts Merge
        // Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
        //Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
        //After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
        // ip- accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        // o/p is [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        // alg- connect all names which have common email using UF
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            //to find out common emails-- use map of email and index of account (cannt use string i.e name for UF). AND connect all related indexes
            UF uf = new UF(accounts.size());
            Map<String, Integer> emailGroup = new HashMap<>();
            //step1: union related indexes
            for(int i =0; i < accounts.size(); i++) {
                for(int j =1; j < accounts.get(i).size(); j++) {
                    if(emailGroup.containsKey(accounts.get(i).get(j)))
                        emailGroup.put(accounts.get(i).get(j), i);
                    else
                        uf.union(i, emailGroup.get(accounts.get(i).get(j))); //union 2 indexes
                }
            }
            //step2: form merged accounts using above created roots
            Map<Integer, List<String>> mergedAccounts = new HashMap<>();
            for(String email : emailGroup.keySet()) {
                int root = uf.find(emailGroup.get(email));
                if(!mergedAccounts.containsKey(root))
                    mergedAccounts.put(root, new ArrayList<String>());
                mergedAccounts.get(root).add(email);
            }
            //step3 : sort each root and add name
            List<List<String>> res = new ArrayList<>();
            for(int currRoot : mergedAccounts.keySet()) {
                List<String> emails = mergedAccounts.get(currRoot);
                Collections.sort(emails);
                emails.add(0, accounts.get(currRoot).get(0)); //add name
                res.add(emails);
            }
            return res;
        }

    }
}
