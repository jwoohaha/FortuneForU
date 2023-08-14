<template>
  <tr class="table-row">
    <th class="table-header"><p class="table-header-text">{{ headerText }}</p></th>
    <td class="table-description">
      <slot></slot>
    </td>
  </tr>
</template>

<script>
export default {

  props: {
    headerText: String
  }
}
</script>

<style>
.table-row{
  display: flex;
  margin-bottom: 20px;
}

.table-header{
  width: 20%;
  /* background-color: blue; */
  opacity: 5;
}

.table-header-text{
  display: flex;
  height: 100%;
  justify-content: center;
  align-items: center;
}

.table-description{
  width: 80%;
  /* background-color: green; */
  opacity: 5;
}

</style>