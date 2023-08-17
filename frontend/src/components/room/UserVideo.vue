<template>
<div v-if="streamManager">
	<ov-video :stream-manager="streamManager"/>
	<div class="username"><p>{{ clientData }}</p></div>
</div>
</template>

<script>
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
	},
};
</script>

<style lang="scss" scoped>
.username{
	font-size: 30px;
	font-family: 'Noto Sans KR', 'Serif';
	font-weight: 600;
	width: 100%;
	display: flex;
	justify-content: center;
}
.username p {
	margin-top: 20px;
	margin-bottom: 20px;
	background: #c2b1e6;
	width: 30%;
}
</style>
