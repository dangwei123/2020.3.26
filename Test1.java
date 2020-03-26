给定两个数组，编写一个函数来计算它们的交集
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1=new HashSet<>();
        for(int i=0;i<nums1.length;i++){
            if(!set1.contains(nums1[i])){
                set1.add(nums1[i]);
            }
        }
        Set<Integer> set2=new HashSet<>();
        for(int i=0;i<nums2.length;i++){
            if(!set2.contains(nums2[i])){
                set2.add(nums2[i]);
            }
        }
        List<Integer> list=new LinkedList<>();
        for(Integer i:set2){
            if(set1.contains(i)){
                list.add(i);
            }
        }
        int size=list.size();
        int[] res=new int[size];
        for(int i=0;i<size;i++){
            res[i]=list.remove(0);
        }
            return res;
    }
}

给定两个数组，编写一个函数来计算它们的交集。
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list=new ArrayList<>();
        for(int i=0,j=0;i<nums1.length&&j<nums2.length;){
            if(nums1[i]<nums2[j]){
                i++;
            }else if(nums1[i]>nums2[j]){
                j++;
            }else{
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int size=list.size();
        int[] res=new int[size];
        int i=0;
       for(Integer num:list){
           res[i++]=num;
       }
       return res;
    }
}

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list=new ArrayList<>();
        for(int i:nums1){
            list.add(i);
        }
        List<Integer> res=new ArrayList<>();
        for(int i:nums2){
            if(list.contains(i)){
                res.add(i);
                list.remove(Integer.valueOf(i));
            }
        }
        int size=res.size();
        int[] arr=new int[size];
        int i=0;
        for(Integer j:res){
            arr[i++]=j;
        }
        return arr;
    }
}

给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数
class Solution {
    public int findDuplicate(int[] nums) {
        int fast=nums[0];
        int slow=nums[0];
        do{
            fast=nums[fast];
            fast=nums[fast];
            slow=nums[slow];
        }while(fast!=slow);
        fast=nums[0];
        while(fast!=slow){
            fast=nums[fast];
            slow=nums[slow];
        }
        return fast;
    }
}

给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        if(m>n){
            int[] tmp=nums1;
            nums1=nums2;
            nums2=tmp;

            int t=m;
            m=n;
            n=t;
        }
        int left=0;
        int right=m;
        int half=(m+n+1)/2;
        while(left<=right){
            int i=(left+right)/2;
            int j=half-i;
            if(i>0&&nums1[i-1]>nums2[j]){
                right=i-1;
            }else if(i<m&&nums1[i]<nums2[j-1]){
                left=i+1;
            }else{
                int maxleft;
                if(i==0){
                    maxleft=nums2[j-1];
                }else if(j==0){
                    maxleft=nums1[i-1];
                }else{
                    maxleft=Math.max(nums1[i-1],nums2[j-1]);
                }

                if((m+n)%2!=0){
                    return maxleft;
                }

                int minright;
                if(i==m){
                    minright=nums2[j];
                }else if(j==n){
                    minright=nums1[i];
                }else{
                    minright=Math.min(nums1[i],nums2[j]);
                }
                return (maxleft+minright)/2.0;
            }
        }
        return 0.0;
    }
}

            
给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int left=0;
        int right=nums[nums.length-1]-nums[0];
        while(left<right){
            int mid=(left+right)>>>1;
            int count=0;
            int start=0;
            for(int i=0;i<nums.length;i++){
                while(start<i&&nums[i]-nums[start]>mid){
                    start++;
                }
                count+=i-start;
            }
            if(count<k){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return left;
    }
}

给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
class Solution {
    public int splitArray(int[] nums, int m) {
        int sum=0;
        int max=nums[0];
        for(int i:nums){
            sum+=i;
            max=Math.max(i,max);
        }
        int left=max;
        int right=sum;
        while(left<right){
            int mid=(left+right)>>>1;
            int backet=1;
            int tmp=0;
            for(int i:nums){
                tmp+=i;
                if(tmp>mid){
                    backet++;
                    tmp=i;
                }
                    
                
            }
            if(backet>m){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return left;
    }
}