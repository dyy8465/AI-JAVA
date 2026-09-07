全栈智能旅行助手 (Travel AI) 项目
项目旨在打造一个“AI 行程规划师”，核心功能包括：

智能行程定制：用户输入目的地、预算和游玩天数，系统能自动生成详细的每日行程安排（包含早中晚的景点、交通、餐饮及费用明细）。
AI 实时对话 (SSE)：支持类似 ChatGPT 的打字机效果（Server-Sent Events），用户可以向 AI 提问关于旅行的各种细节，获得实时流式解答。
用户系统闭环：拥有完整的注册、登录功能，包含密码一致性校验，使用 JWT Token 进行状态维持和接口鉴权。
目的地选择：支持通过拼音或汉字搜索全国城市，并提供 A-Z 索引的直观城市列表。
2. 技术栈选型
这是一个标准的现代化全栈架构，非常适合作为个人作品集或商业项目的起点。

前端 (travel-ai-h5)

核心框架：Vue 3 + Composition API
构建工具：Vite (极速的本地开发和打包)
UI 组件库：Vant 4 (非常适合移动端 H5 体验的轻量级组件库)
路由与状态：Vue Router 处理页面跳转和登录拦截，LocalStorage 存储 Token。
网络请求：Axios (常规接口) + 原生 Fetch API (处理 SSE 流式响应)。

后端 (travel-java)
核心框架：Java + Spring Boot (提供 RESTful API)
持久层框架：MyBatis-Plus (极大简化了对 MySQL 的增删改查)
数据库：MySQL (存储用户数据及未来可能保存的行程记录)
安全与鉴权：JJWT (JSON Web Token)，通过自定义拦截器保护私密接口。
AI 大模型接入：集成了 SiliconFlow (DeepSeek-V4-Flash)，通过后端进行提示词组装和 API 调用，将结果返回给前端。
