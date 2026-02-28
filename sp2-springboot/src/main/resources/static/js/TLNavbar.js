// 將 nav 標籤內的內容定義為一個 tl-navbar 組件
export default {
    props: ['userName'], // 接收父組件傳入的 user.name
    template: `
    <nav class="bg-sky-800 text-white p-4 shadow-lg flex justify-between items-center">
        <div @click="goHome" class="text-3xl font-bold tracking-tighter cursor-pointer hover:opacity-80 transition-all">TL BANK</div>
        <div class="flex items-center space-x-4">
            <span v-if="userName" class="font-medium">歡迎，{{ userName }}</span>
            <button v-if="userName" @click="$emit('on-logout')" 
                    class="bg-sky-800 hover:bg-sky-900 px-4 py-1 rounded text-sm transition">
                登出
            </button>
            <div v-else class="space-x-2">
                <a href="login.html" class="hover:underline">登入</a>
                <a href="addMember.html" class="hover:underline">註冊</a>
            </div>
        </div>
    </nav>
    `,
	methods: {
		goHome() {
			window.location.href = "index.html";
		}
	}
};