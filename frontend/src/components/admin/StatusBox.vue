<template>
  <div class="status-box-container" >
    <div class="status-box-content" :style="{backgroundColor: statusCode.color}">
      <div>{{ statusCode.label }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props:{
    statusCode: Object
  }
}
</script>

<style lang="scss">
.status-box-container{
  display: flex;
  width: 100%;
  justify-content: center;
}
.status-box-content{
  display: flex;
  width: 65%;
  height: 35px;
  align-items: center;
  justify-content: center;
  border-radius: 10px;
  font-size: 16px;
  font-style: normal;
  font-weight: 700;
  color: #FFFFFF;
}
</style>