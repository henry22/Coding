/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    const map = new Map();

    for(let i = 0; i < nums.length; i++) {
        let rest = target - nums[i];

        if(map.has(rest)) {
            return [i, map.get(rest)]
        }

        map.set(nums[i], i);
    }

    return [];
};