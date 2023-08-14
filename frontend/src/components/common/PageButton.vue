<template>
    <div class="pagebtn-list">
        <div class="leftbtn pagebtn" v-if="pageType!='TARO'" style="background:'#F6B5C6'">◀</div>
        <div class="leftbtn pagebtn" v-else>◀</div>
        <div v-for="pageNum in (totalPages >= 5 ? 5 : totalPages)" 
            class="pagebtn"
            :key="pageNum" 
            @click="goToPage(pageNum-1)">
            <div v-if="pageType!='TARO'" style="background:'#F6B5C6'">{{ pageNum }}</div>
            <div v-else>{{ pageNum }}</div>
        </div>
        <div class="rightbtn pagebtn" v-if="pageType!='TARO'" style="background:'#F6B5C6'">▶</div>
        <div class="rightbtn pagebtn" v-else>▶</div>
    </div>    
</template>

<script>
export default {
    props: {
        totalPages: {
        type: Number,
        required: true,
        },
        pageType: {
            type: String,
            required: false
        }
    },
    methods: {
        goToPage(pageNum) {
            this.$emit('page-changed', pageNum);
        },
    },
}
</script>

<style lang="scss" scoped>
.pagebtn-list {
    display: flex;
    width: 293px;
    height: 45.635px;
    padding: 0px 3px 5.635px 0px;
    justify-content: space-between;
    align-items: center;
}
.pagebtn-list div{
    width: 40px;
    height: 40px;
    border-radius: 100px;
    background: #9C7AE7;
    color: #FFF;
    font-size: 16px;
    font-style: normal;
    font-weight: 700;
    display : flex;
    justify-content : center;
    align-items : center;
}
.pagebtn {
    cursor: pointer;
}
</style>