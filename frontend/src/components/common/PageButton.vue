<template>
    <div class="pagebtn-list">
        <div class="leftbtn">◀</div>
        <div class="onebtn">1</div>
        <div class="twobtn">2</div>
        <div class="threebtn">3</div>
        <div class="rightbtn">▶</div>
    </div>    
</template>

<script>
export default {
    
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
</style>