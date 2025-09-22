/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var minSplitMerge = function(nums1, nums2) {
    const n = nums1.length;
    const target = nums2.join(',');
    const queue = [[nums1, 0]];
    const visited = new Set();

    visited.add(nums1.join(','));

    while(queue.length > 0) {
        const [arr, step] = queue.shift();
        const result = arr.join(',');

        if(result === target) {
            return step;
        }

        for(let i = 0; i < n; i++) {
            for(let j = i; j < n; j++) {
                const left = arr.slice(0, i);
                const sub = arr.slice(i, j + 1);
                const right = arr.slice(j + 1);
                const remaining = left.concat(right);

                for(let k = 0; k <= remaining.length; k++) {
                    const next = [...remaining.slice(0, k), ...sub, ...remaining.slice(k)];
                    const key = next.join(',');

                    if(!visited.has(key)) {
                        visited.add(key);
                        queue.push([next, step + 1]);
                    }
                }
            }
        }
    }

    return -1;
};